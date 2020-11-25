package com.terukiss.smssender.Permission;


import android.Manifest;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PermissionValue {

    String[] permission = {
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS,
    };
}
