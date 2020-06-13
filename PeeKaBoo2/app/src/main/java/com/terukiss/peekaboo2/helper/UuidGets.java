package com.terukiss.peekaboo2.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class UuidGets {

    Context context;
    JeongLog jeongLog ;
    public UuidGets(Context context) {
        this.context = context;
        jeongLog = new JeongLog(context, "UUID");
    }

    public String getUUID() {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if(telephonyManager == null)
        {
            jeongLog.logD("전화번호를 가져올수 없습니다 . ");

        }
        else
        {
            int uSimState = telephonyManager.getSimState();

            switch (uSimState)
            {

                case TelephonyManager.SIM_STATE_UNKNOWN:
                    // 유심 상태를 알수 없는 경우
                    break;

                case TelephonyManager.SIM_STATE_ABSENT:
                    // 유심이 없는 경우
            }
        }
    }
}
