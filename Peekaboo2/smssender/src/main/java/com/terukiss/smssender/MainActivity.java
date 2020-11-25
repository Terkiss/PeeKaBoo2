package com.terukiss.smssender;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

import com.terukiss.smssender.Permission.PermissionRequst;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionRequst permissionRequst = new PermissionRequst();

        permissionRequst.requestPermission(MainActivity.this);

    }

    public void Onclick(View v) {
        SmsManager smsManager = SmsManager.getDefault();

        String m1 = "봇 데이터 테스트 입니다";
        String[] sms = new String[10];
        String m2 = "지금 부터 1부터 10까지 수를 전송 하겟습니다";

        for(int i = 0 ; i < sms.length; i++)
        {
            sms[i] = "현재 진행수 :: "+i;
            try {
                smsManager.sendTextMessage("01039766585", null, sms[i], null, null);
                Thread.sleep(50);
            }
            catch (Exception e)
            {

            }

        }






    }
}
