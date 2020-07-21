package com.terukiss.peekaboo2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.terukiss.peekaboo2.activity.ContentBindActivity;
import com.terukiss.peekaboo2.activity.UserProfile;
import com.terukiss.peekaboo2.helper.ConnectionInfo;
import com.terukiss.peekaboo2.helper.DataBaseInfo;
import com.terukiss.peekaboo2.helper.DatabaseManager;
import com.terukiss.peekaboo2.helper.JeongLog;
import com.terukiss.peekaboo2.helper.PermissionRequst;
import com.terukiss.peekaboo2.helper.UuidGets;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int delayTime = 2000;
    DatabaseManager databaseManager;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        PermissionRequst permissionRequst = new PermissionRequst();
        while(true)
        {
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
            )
            {
                permissionRequst.requestPermission(MainActivity.this);
            }
            else
            {
                new Handler().postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), ContentBindActivity.class );

                        databaseManager = DatabaseManager._Instance.initialization(getApplicationContext());

                        // 마지막 연결 을 저장한 테이블에 있는지 검사 합니다.
                        Cursor cursor = databaseManager.selectDataForTable(DataBaseInfo._TableLastConnect);

                        //  데이터 베이스에서 추출

                        while(cursor.moveToNext())
                        {
                            String nick = cursor.getString(0);
                            String address = cursor.getString(1);
                            String port = cursor.getString(2);

                            ConnectionInfo.ServerNick = nick;
                            ConnectionInfo.ServerHostName = address;
                            ConnectionInfo.ServerPort = port;
                        }


                        Cursor isUserProfile = databaseManager.selectDataForTable(DataBaseInfo._TableUser);


                        // 데이터 베이스의 저장된 데이터 개수  가 1 이하일경우 -> 저장이 안되어 있음
                        if(isUserProfile.getCount() < 1)
                        {
                            int n = new Random().nextInt(99999);
                            String nick =String.valueOf(n);

                            UuidGets uuidGets = new UuidGets(getApplicationContext());
                            String uuid = uuidGets.getUUID(MainActivity.this);
                            JeongLog jeongLog = new JeongLog("uuid");

                            jeongLog.logD("uuid :: "+uuid);
                            String photo = "-1";

                           String[] columnName = databaseManager.getColumnList(DataBaseInfo._TableUser);

                            databaseManager.insertData(DataBaseInfo._TableUser, columnName, new String[]{
                                    nick,
                                    uuid,
                                    photo
                            });

                            UserProfile.setProfileNickName(nick);
                            UserProfile.setProfileUUID(uuid);
                            UserProfile.setProfilePicturePath(photo);
                        }
                        else
                        {
                            isUserProfile.moveToNext();

                            UserProfile.setProfileNickName( isUserProfile.getString(0) );
                            UserProfile.setProfileUUID( isUserProfile.getString(1) );
                            UserProfile.setProfilePicturePath( isUserProfile.getString(2) );
                        }
                        startActivity(intent);
                        finish();
                    }
                },delayTime);
                break;
            }
        }


        /*
//        new Handler().postDelayed(new Runnable() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), ContentBindActivity.class );
//
//                databaseManager = DatabaseManager._Instance.initialization(getApplicationContext());
//
//                // 마지막 연결 을 저장한 테이블에 있는지 검사 합니다.
//                Cursor cursor = databaseManager.selectDataForTable(DataBaseInfo._TableLastConnect);
//
//                //  데이터 베이스에서 추출
//
//                while(cursor.moveToNext())
//                {
//                    String nick = cursor.getString(0);
//                    String address = cursor.getString(1);
//                    String port = cursor.getString(2);
//
//                    ConnectionInfo.ServerNick = nick;
//                    ConnectionInfo.ServerHostName = address;
//                    ConnectionInfo.ServerPort = port;
//                }
//
//
//                Cursor isUserProfile = databaseManager.selectDataForTable(DataBaseInfo._TableUser);
//
//
//                // 데이터 베이스의 저장된 데이터 개수  가 1 이하일경우 -> 저장이 안되어 있음
//                if(isUserProfile.getCount() < 1)
//                {
//                    int n = new Random().nextInt(99999);
//                    String nick =String.valueOf(n);
//
//                    UuidGets uuidGets = new UuidGets(getApplicationContext());
//                    String uuid = uuidGets.getUUID(MainActivity.this);
//                    JeongLog jeongLog = new JeongLog("uuid");
//
//                    jeongLog.logD("uuid :: "+uuid);
//                    String photo = "-1";
//
//                   String[] columnName = databaseManager.getColumnList(DataBaseInfo._TableUser);
//
//                    databaseManager.insertData(DataBaseInfo._TableUser, columnName, new String[]{
//                            nick,
//                            uuid,
//                            photo
//                    });
//
//                    UserProfile.setProfileNickName(nick);
//                    UserProfile.setProfileUUID(uuid);
//                    UserProfile.setProfilePicturePath(photo);
//                }
//                else
//                {
//                    isUserProfile.moveToNext();
//
//                    UserProfile.setProfileNickName( isUserProfile.getString(0) );
//                    UserProfile.setProfileUUID( isUserProfile.getString(1) );
//                    UserProfile.setProfilePicturePath( isUserProfile.getString(2) );
//                }
//                startActivity(intent);
//                finish();
//            }
//        },delayTime);
*/
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
