package com.terukiss.peekaboo2.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.Network.ConnectionInfo;
import com.terukiss.peekaboo2.helper.Network.CsharpServerCommunication;
import com.terukiss.peekaboo2.helper.DataBase.DataBaseInfo;
import com.terukiss.peekaboo2.helper.DataBase.DatabaseManager;
import com.terukiss.peekaboo2.helper.JeongLog;
import com.terukiss.peekaboo2.helper.Network.PeeKaBooProtocol;
import com.terukiss.peekaboo2.helper.PeekabooAlartDialog;

import java.util.ArrayList;


public class Fragment_Chat extends Fragment implements View.OnClickListener{

    View view = null;
    private FloatingActionButton fabBtn, DebugFabButton;
    private JeongLog jeongLog  ;

    private RecyclerView recyclerView;
    private RoomListRecyclerViewAdapter adapter;
    private ArrayList<String> roomTitle = new ArrayList<>() ;
    public Fragment_Chat() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(view == null)
        {
            jeongLog = new JeongLog("FragmentTest");
            view = inflater.inflate(R.layout.fragment__chat, container, false);

            fabBtn = view.findViewById(R.id.fab);
            DebugFabButton = view.findViewById(R.id.dataDel);

            fabBtn.setOnClickListener(this);
            DebugFabButton.setOnClickListener(this);

            recyclerView = view.findViewById(R.id.chat_RecyclerView);

            if( !(ConnectionInfo.ServerHostName.length() < 1))
            {
                CsharpServerCommunication send = new CsharpServerCommunication("Communication");
                String command = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.RoomRequst, UserProfile.getProfileUUID());
                send.sendCsharpServer(command);
            }

            adapter = new RoomListRecyclerViewAdapter(getContext(),roomTitle);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            recyclerView.setAdapter(adapter);

            // 방 리로드 2초마다 갱신을 해야함  쓰레드나 안드로이드 백그라운드 서비스를 사용
            reloadRecyclerView();
        }

        return view;
    }

    // 비어 있으면 트루 안비어 있으면 폴스
    private boolean isEmpty(String str)
    {
        if(str.length() < 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        final View viewObject = v;
        if(id == R.id.fab)
        {


            boolean isStrIsEmpty = isEmpty(ConnectionInfo.ServerPort) || isEmpty(ConnectionInfo.ServerHostName) || isEmpty(ConnectionInfo.ServerNick);

            if(!isStrIsEmpty)
            {
                // 방 목록 생성
                PeekabooAlartDialog alartDialog = new PeekabooAlartDialog(getContext());

                View view =  getLayoutInflater().inflate(R.layout.custom_dialog_01, null, false);

                alartDialog.dialogCreate("대화방 생성");
                alartDialog.customDialogView(view);

                final EditText roomName = view.findViewById(R.id.chat_RoomName);
                final EditText roomJoinMax = view.findViewById(R.id.chat_MaxNumber);
                final EditText roomTag = view.findViewById(R.id.chat_RoomTag);
                final EditText roomPW = view.findViewById(R.id.chat_openPw);

                alartDialog.positiveButtonCreate("create", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jeongLog.logD("방만듬");
                        jeongLog.logD("roomName"+roomName.getText());
                        jeongLog.logD("roomJoinMax"+roomJoinMax.getText());
                        jeongLog.logD("roomTag"+roomTag.getText());
                        jeongLog.logD("roomPW"+roomPW.getText());

                        if(roomName.getText().toString().length() < 1)
                        {
                            // 방이름이 비엇을경우 서버 전송을 안함
                            Snackbar.make(viewObject, "방이름은 절대로 비울수 없습니다", Snackbar.LENGTH_LONG).show();
                            // 재로딩
                            reloadRecyclerView();
                            return;
                        }

                        StringBuilder dd  = new StringBuilder();
                        dd.append("create");
                        dd.append(":");
                        dd.append(roomName.getText().toString());
                        dd.append(":");
                        dd.append(roomJoinMax.getText().toString());
                        dd.append(":");
                        dd.append(roomTag.getText().toString());
                        dd.append(":");
                        dd.append(roomPW.getText().toString());

                        //    채팅방 생성 프로토콜
                        //    *  방 생성시
                        //    *  1 명령어
                        //    *  2 방이름
                        //    *  3 최대 접속 인원
                        //    *  4 방태그
                        //    *  5 방 입장 패스워드\
                        //    *  6 방장 uuid
                        String pw = roomPW.getText().toString();
                        if(pw.length() < 1 )
                        {
                            jeongLog.logD("방비밀 번호가 입력이 안될경우 공개방");
                            pw = "-1";
                        }


                        String command = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.RoomCreate,
                                roomName.getText().toString(),
                                roomJoinMax.getText().toString(),
                                roomTag.getText().toString(),
                                pw,
                                UserProfile.getProfileUUID()
                                );

                        jeongLog.logD(" 명령어 생성 검증 :: "+command);
                        jeongLog.logD("명령어 길이 : "+command.length());


                        // 데이터 베이스에 데이터를 입력
                        String[] roomTBLColumn = DatabaseManager._Instance.getColumnList(DataBaseInfo._TableRoom);
                        DatabaseManager._Instance.insertDataForDataDeduplication(
                                DataBaseInfo._TableRoom,
                                roomTBLColumn,
                                new String[]
                                        {
                                                roomName.getText().toString(),
                                                roomJoinMax.getText().toString(),
                                                roomTag.getText().toString(),
                                                pw,
                                                UserProfile.getProfileUUID()
                                        }
                        );


                        // 접속된 채팅서버에 만든 방을 알림
                        CsharpServerCommunication send = new CsharpServerCommunication("Communication");

                        send.sendCsharpServer(command);

                        // 재로딩
                        reloadRecyclerView();
                    }
                });

                alartDialog.show();
            }
            else
            {
                Snackbar.make(v, "서버 연결을 먼저 하시고 누르세욤 ", Snackbar.LENGTH_LONG).show();
            }

        }
        else if(id == R.id.dataDel)
        {
            // 방목록 db 초기화
            DatabaseManager._Instance.deleteDataForTable(DataBaseInfo._TableRoom);
            reloadRecyclerView();
        }
    }




    private void reloadRecyclerView()
    {
        roomTitle.clear();
        if( !(ConnectionInfo.ServerHostName.length() < 1))
        {
            CsharpServerCommunication send = new CsharpServerCommunication("Communication");
            String command = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.RoomRequst, UserProfile.getProfileUUID());
            send.sendCsharpServer(command);
        }


        Cursor cursor = DatabaseManager._Instance.selectDataForTable(DataBaseInfo._TableRoom);

        while(cursor.moveToNext())
        {
            roomTitle.add(cursor.getString(1));
        }


        adapter.setItem(roomTitle);
        adapter.notifyDataSetChanged();
    }
}
