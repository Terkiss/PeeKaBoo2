package com.terukiss.peekaboo2.helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

public class UuidGets {

    Context context;
    JeongLog jeongLog;

    public UuidGets(Context context) {
        this.context = context;
        jeongLog = new JeongLog(context, "UUID");
    }

    @SuppressLint("HardwareIds")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getUUID(Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String telNumber = "";
        if (telephonyManager == null) {
            jeongLog.logD("전화번호를 가져올수 없습니다 . ");

        } else {
            try {

                int uSimState = telephonyManager.getSimState();

                switch (uSimState) {

                    case TelephonyManager.SIM_STATE_UNKNOWN:
                        // 유심 상태를 알수 없는 경우
                        break;

                    case TelephonyManager.SIM_STATE_ABSENT:
                        // 유심이 없는 경우
                        break;
                    case TelephonyManager.SIM_STATE_PERM_DISABLED:
                        // 유심 오류 영구적인 사용 중지
                        break;
                    case TelephonyManager.SIM_STATE_CARD_IO_ERROR:
                        // 유심 있음 , 오류 상태
                        break;
                    case TelephonyManager.SIM_STATE_CARD_RESTRICTED:
                        // 유심 있지만, 통신사 제한으로 사용 불가
                        break;
                    default:

                        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                        )
                        {
                            PermissionRequst permissionRequst = new PermissionRequst();
                            permissionRequst.requestPermission(activity);
                        }


                        telNumber = telephonyManager.getLine1Number();
                        jeongLog.logD(telNumber);
                        telNumber = telNumber.replace("+82", "0");
                        telNumber = telNumber +"@";
                        jeongLog.logD("sim check, Tel No, " + telNumber);
                        break;
                }
            }
            catch (Exception e)
            {
                jeongLog.logD("sim check, Exception: "+e.toString());
                e.printStackTrace();
            }

        }

        return telNumber;
    }
}
