package com.terukiss.smssender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class MyReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        String str = "";
        if(bundle != null)
        {
            Object[] pdus = (Object[]) bundle.get("pdus");

            SmsMessage[] messages = new SmsMessage[pdus.length];

            for(int i = 0 ; i < messages.length; i++)
            {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i],"3gpp");

                str += messages[i].getOriginatingAddress() +" // 메세지 : "+messages[i].getMessageBody().toString()+" \n";
            }
        }
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
