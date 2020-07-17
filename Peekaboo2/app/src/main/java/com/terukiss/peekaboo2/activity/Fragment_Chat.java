package com.terukiss.peekaboo2.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.ConnectionInfo;
import com.terukiss.peekaboo2.helper.CsharpServerCommunication;
import com.terukiss.peekaboo2.helper.JeongLog;
import com.terukiss.peekaboo2.helper.PeeKaBooProtocol;
import com.terukiss.peekaboo2.helper.PeekabooAlartDialog;


public class Fragment_Chat extends Fragment implements View.OnClickListener{

    View view = null;
    private FloatingActionButton fabBtn;
    private JeongLog jeongLog  ;

    private RecyclerView recyclerView;
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

            fabBtn.setOnClickListener(this);

            recyclerView = view.findViewById(R.id.chat_RecyclerView);

            CsharpServerCommunication send = new CsharpServerCommunication("Communication");

            String command = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.RoomRequst, UserProfile.getProfileUUID());

            //send.sendCsharpServer(command);


        }
        CsharpServerCommunication send = new CsharpServerCommunication("Communication");
        String command = PeeKaBooProtocol.commandGenerator(PeeKaBooProtocol.RoomRequst, UserProfile.getProfileUUID());
        send.sendCsharpServer(command);
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


                        CsharpServerCommunication send = new CsharpServerCommunication("Communication");

                        send.sendCsharpServer(command);


                    }
                });

                alartDialog.show();
            }
            else
            {
                Snackbar.make(v, "서버 연결을 먼저 하시고 누르세욤 ", Snackbar.LENGTH_LONG).show();
            }

        }
    }


}
