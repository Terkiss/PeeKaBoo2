package com.terukiss.peekaboo2.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.app.DialogCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.terukiss.peekaboo2.R;
import com.terukiss.peekaboo2.helper.JeongLog;

import java.io.ObjectStreamException;
import java.util.ArrayList;


public class Fragment_Server_List extends Fragment implements View.OnClickListener {


    private FloatingActionButton fab, fab1, fab2;
    View view = null;
    RecyclerView recyclerView;

    JeongLog jeongLog = null;
    public Fragment_Server_List() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        }

        return view;
    }



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
                //Toast.makeText(getActivity(), "Button1", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 다이어로그로 빌더로 구움
                //https://lktprogrammer.tistory.com/155
               // builder.setTitle("어서와 처음이지?").setMessage("버튼 추가 예제야");
                builder.setTitle("버튼 추가 예제야");
//                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        jeongLog.logD("긍정의 버튼 눌림");
//                    }
//                });
//                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        jeongLog.logD("부정의 버튼 눌림 "+which);
//                    }
//                });
//
//                builder.setNeutralButton("????", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        jeongLog.logD("이건 뭔 버튼 일까?");
//                    }
//                });



//                builder.setItems(R.array.LAN, new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String[] items = getResources().getStringArray(R.array.LAN);
//
//                        jeongLog.logD("목록 아이템 which value :: "+which+"  item :: "+items[which]);
//                    }
//                });


//
//                final ArrayList<String> selectedItems = new ArrayList<String>();
//
//                final String[] items = getResources().getStringArray(R.array.LAN);
//
//                builder.setTitle("리스트 추가 예제");
//
//                builder.setMultiChoiceItems(R.array.LAN, null, new DialogInterface.OnMultiChoiceClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int pos, boolean isChecked)
//                    {
//                        if(isChecked == true) // Checked 상태일 때 추가
//                        {
//                            selectedItems.add(items[pos]);
//                        }
//                        else				  // Check 해제 되었을 때 제거
//                        {
//                            for(int i = 0; i< selectedItems.size(); i++)
//                            {
//                               Object object = selectedItems.get(i);
//                               if(object.equals(items[pos]))
//                               {
//                                   selectedItems.remove(i);
//                               }
//                            }
//
//                        }
//                    }
//                });
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int pos)
//                    {
//                        String SeletedItemsString = "";
//
//                        for(int i =0; i<selectedItems.size();i++)
//                        {
//                            SeletedItemsString =  SeletedItemsString + "," + selectedItems.get(i);
//                        }
//
//                        Toast toast = Toast.makeText(getContext(), "선택 된 항목은 :" + SeletedItemsString,Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
//                        toast.show();
//                    }
//                });


                final String[] items = getResources().getStringArray(R.array.LAN);
                final ArrayList<String> selectedItem  = new ArrayList<String>();
                selectedItem.add(items[0]); // 아이템의 첫번쨰를 선택해 놓음

                builder.setTitle("리스트 추가 예제");

                // 싱글 초이스 리스너
                builder.setSingleChoiceItems(R.array.LAN, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int pos)
                    {
                        selectedItem.clear();
                        selectedItem.add(items[pos]);
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int pos)
                    {
                        Toast toast = Toast.makeText(getContext(), "선택된 항목 : " + selectedItem.get(0), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });




                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                jeongLog.logD("floatting add action button activate");
                break;
            case R.id.fab_del:
                anim();
                //Toast.makeText(getActivity(), "Button2", Toast.LENGTH_SHORT).show();
                jeongLog.logD("floatting delete action button activate");
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
}
