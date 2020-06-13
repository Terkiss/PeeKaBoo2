package com.terukiss.peekaboo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.terukiss.peekaboo2.helper.JeongLog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {

    Socket socket;
    EditText editText;
    TextView textView;
    JeongLog jeongLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        jeongLog = new JeongLog(getApplicationContext(), "socketTest");

        Button button ;

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jeongLog.logD("소켓을 테스트 합니다 ");
                clickProcess();
            }
        });

    }

    private void clickProcess() {
        BufferedReader in = null;
        BufferedWriter out = null;





        try {
            socket = new Socket("192.168.0.25", 10000);


           // in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);

            jeongLog.logD("소켓을 테스트 합니다 ");
            out.write(editText.getText() + " \n");
            out.flush();
            byte[] buf = new byte[200];
            socket.getInputStream().read(buf);
           // String receiveStr = in.readLine();

            String aa = "";
            for (int i = 0; i < buf.length; i++)
            {
                if(buf[i]==0)
                {
                    byteArrayOutputStream.write(buf, 0, i-1);
                    aa += byteArrayOutputStream.toString("UTF-8");
                    break;
                }


            }

            jeongLog.logD(aa) ;

            textView.setText(aa);

            //textView.setText("receiveStr : "+receiveStr);

            //in.close();
            out.close();
            socket.close();
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
