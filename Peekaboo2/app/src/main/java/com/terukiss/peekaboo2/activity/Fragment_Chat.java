package com.terukiss.peekaboo2.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.JeongLog;
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

        }
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.fab)
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

                    // 데이터 프,로토콜
                    // 1 명령어
                    // 2 최대 접속 인원
                    // 3 방태그
                    // 4 방 입장 패스워드
                    String sender = dd.toString();

                    jeongLog.logD("sender data :: "+sender);
                }
            });

            alartDialog.show();
        }
    }


}
