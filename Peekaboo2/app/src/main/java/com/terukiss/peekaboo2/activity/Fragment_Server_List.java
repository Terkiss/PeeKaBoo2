package com.terukiss.peekaboo2.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.core.app.DialogCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.terukiss.peekaboo2.R;


public class Fragment_Server_List extends Fragment implements View.OnClickListener {


    private FloatingActionButton fab, fab1, fab2;
    View view = null;
    RecyclerView recyclerView;
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
                Toast.makeText(getActivity(), "Floating Action Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_add:
                anim();
                Toast.makeText(getActivity(), "Button1", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // 다이어로그로 빌더로 구움
                //https://lktprogrammer.tistory.com/155
                break;
            case R.id.fab_del:
                anim();
                Toast.makeText(getActivity(), "Button2", Toast.LENGTH_SHORT).show();
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
