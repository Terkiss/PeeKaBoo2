package com.terukiss.peekaboo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.terukiss.peekaboo2.helper.JeongLog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Socket socket;
    EditText editText;
    JeongLog jeongLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jeongLog = new JeongLog(getApplicationContext(), "socketTest");

        Button button ;

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);


        try {
            socket = new Socket("192.168.0.25",10000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jeongLog.logD("소켓을 테스트 합니다 ");
                //clickProcess();
            }
        });

    }

    private void clickProcess()
    {
        BufferedReader in = null;
        BufferedWriter out = null;


        try {
            in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));


            out.write("서버로 데이터를 전송 할게요 \n");

            String receiveStr = in.readLine();

            TextView textView = findViewById(R.id.textView);

            textView.setText(receiveStr);

            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
