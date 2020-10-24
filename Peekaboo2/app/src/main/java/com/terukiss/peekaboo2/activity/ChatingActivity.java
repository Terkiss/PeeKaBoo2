package com.terukiss.peekaboo2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.terukiss.peekaboo2.R;

public class ChatingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);

        ImageView detailImageView = findViewById(R.id.detailButton); // 사진 전송 문자 전송 동영상 전송 추후 지원 파일전송 등

        EditText editText = findViewById(R.id.inputFilled); // 유저 데이터 입력 공간

        ImageView sendButton  = findViewById(R.id.acceptButton); // 현재 까지 입력된 문자를 처리하는 버튼


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id d)
    }
}
