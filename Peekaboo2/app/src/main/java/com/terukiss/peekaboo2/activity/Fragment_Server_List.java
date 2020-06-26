package com.terukiss.peekaboo2.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.DialogCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.DataBaseInfo;
import com.terukiss.peekaboo2.helper.DatabaseManager;
import com.terukiss.peekaboo2.helper.JeongLog;
import com.terukiss.peekaboo2.helper.PeekabooAlartDialog;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Objects;


public class Fragment_Server_List extends Fragment implements View.OnClickListener {


    private FloatingActionButton fab, fab1, fab2;
    View view = null;
    ArrayList<ServerListItem> serverListItems = new ArrayList<>();
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    PeekabooAlartDialog alartDialog;
    JeongLog jeongLog = null;
    public Fragment_Server_List() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view == null)
        {
            jeongLog = new JeongLog("FragmentTest");

            view = inflater.inflate(R.layout.fragment__server__list, container, false);

            fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
            fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);

            fab = view.findViewById(R.id.fab);
            fab1 = view.findViewById(R.id.fab_add);
            fab2 = view.findViewById(R.id.fab_del);


            fab.setOnClickListener(this);
            fab1.setOnClickListener(this);
            fab2.setOnClickListener(this);

            adapter = new MyRecyclerViewAdapter(serverListItems, getContext());

            reloadRecycleView();

            recyclerView = view.findViewById(R.id.serverList_RecyclerView);
            jeongLog.logD("여기 됩니까?");
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        return view;
    }



    // Todo 여기는 프로팅 버튼의 리스너
    // 추후에 정리가 필요함!
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                //Toast.makeText(getActivity(), "Floating Action Button", Toast.LENGTH_SHORT).show();
                jeongLog.logD("floatting action button activate");
                break;
            case R.id.fab_add:
                anim();
                alartDialog = new PeekabooAlartDialog(getContext());
                alartDialog.setTitle("서버 등록");
                alartDialog.dialogCreate("");
                View view =  getLayoutInflater().inflate(R.layout.custom_dialog_00, null, false);

                final EditText hostnameEdt = view.findViewById(R.id.hostname);
                final EditText portEdt = view.findViewById(R.id.port);
                final EditText serverNickEdt = view.findViewById(R.id.servernick);


                alartDialog.customDialogView(view);

                alartDialog.positiveButtonCreate("ok", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jeongLog.logD("서버 호스트 주소 : "+hostnameEdt.getText());
                        jeongLog.logD("서버 포트 번호 : "+portEdt.getText());
                        jeongLog.logD("서버 별칭 : "+serverNickEdt.getText());

                        jeongLog.logD("로컬 데이터 베이스에 등록 합니다");
                        if(DatabaseManager._Instance == null)
                        {
                            jeongLog.logD("데이터 베이스가 널이므로 엽니다");
                        }
                        DatabaseManager databaseManager = DatabaseManager._Instance;
                        String[] colums = databaseManager.getColumnList(DataBaseInfo._TableConnectList);
                        databaseManager.insertData(DataBaseInfo._TableConnectList, colums, new String[]{
                                serverNickEdt.getText().toString(),
                                hostnameEdt.getText().toString(),
                                portEdt.getText().toString()
                        });
                        reloadRecycleView();
                    }
                });
                alartDialog.negativeButtonCreate("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "서버 등록을 취소 했습니다", Toast.LENGTH_LONG).show();
                    }
                });

                alartDialog.show();
                jeongLog.logD("floatting add action button activate");
                break;
            case R.id.fab_del:
                anim();
                alartDialog = new PeekabooAlartDialog(getContext());
                alartDialog.setTitle("서버 삭제");
                alartDialog.dialogCreate("");

                final String[] servernic = new String[serverListItems.size()];

                for(int i = 0 ; i < servernic.length; i++)
                {
                    servernic[i] = serverListItems.get(i).getSubtext();

                }
                final ArrayList<String> select = new ArrayList<>();
                alartDialog.addSingleChoiseList(servernic, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select.clear();
                        select.add(servernic[which]);
                    }
                });
                alartDialog.positiveButtonCreate("ok", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseManager databaseManager = DatabaseManager._Instance;
                        if(databaseManager == null)
                        {
                            databaseManager = DatabaseManager._Instance.initialization(Objects.requireNonNull(getContext()));
                        }
                        String temp = select.get(0);
                        int  index = temp.indexOf(": ");
                        temp = temp.substring(index+2);
                        databaseManager.deleteData(DataBaseInfo._TableConnectList, "hostName", temp);
                        reloadRecycleView();
                    }
                });
                alartDialog.negativeButtonCreate("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alartDialog.show();
                break;
        }


    }

    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    // Todo 나중에 여기부분을 수정해야함 지금은 할떄마다 데이터 베이스를 읽음
    // 딱히 지금은 안해도 상관이 없어서 그냥 나둠
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void reloadRecycleView()
    {
        serverListItems.clear();
        DatabaseManager databaseManager = DatabaseManager._Instance;
        if(databaseManager == null)
        {
            databaseManager = DatabaseManager._Instance.initialization(Objects.requireNonNull(getContext()));
        }
        Cursor curor = databaseManager.selectData("select * from "+DataBaseInfo._TableConnectList+";");
        while(curor.moveToNext())
        {
            String serverNick = curor.getString(1);
            String hostName = curor.getString(2);
            String port = curor.getString(3);

            ServerListItem serverListItem = new ServerListItem();
            serverListItem.title = "주소 : "+hostName;
            serverListItem.text = "포트번호 : "+port;
            serverListItem.subtext = "이름 : "+serverNick;

            serverListItems.add(serverListItem);

            jeongLog.logD(" \n"+serverNick+", \n"+hostName+", \n"+port);
        }
        adapter.setItem(serverListItems);
        adapter.notifyDataSetChanged();

    }
}
