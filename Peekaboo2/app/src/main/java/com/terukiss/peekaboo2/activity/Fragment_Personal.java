package com.terukiss.peekaboo2.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.terukiss.peekaboo2.R;


public class Fragment_Personal extends Fragment implements View.OnClickListener {

    public Fragment_Personal() {
        // Required empty public constructor
    }


    View view = null;
    ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if(view == null)
        {
            view = inflater.inflate(R.layout.fragment__personal, container, false);
            imageView = view.findViewById(R.id.gotoAnimation00);
            imageView.setOnClickListener(this);

        }
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id)
        {
            case R.id.gotoAnimation00:
            {
                Intent intent = new Intent(getContext(), AnimationWebView.class);

                startActivity(intent);
                break;
            }
            default:
            {
                Snackbar.make(v, "아직 미구현 입니다 ", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
