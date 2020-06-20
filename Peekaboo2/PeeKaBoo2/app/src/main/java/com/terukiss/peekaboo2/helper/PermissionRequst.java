package com.terukiss.peekaboo2.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

public class PermissionRequst {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestPermission(Activity activity)
    {
        PermissionValue permissionValue = new PermissionValue();
        ActivityCompat.requestPermissions( activity, permissionValue.permission, 100);
    }

}
