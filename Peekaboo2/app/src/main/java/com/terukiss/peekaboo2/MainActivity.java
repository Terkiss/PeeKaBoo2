package com.terukiss.peekaboo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.terukiss.peekaboo2.activity.ContentBindActivity;
import com.terukiss.peekaboo2.helper.ConnectionInfo;
import com.terukiss.peekaboo2.helper.DataBaseInfo;
import com.terukiss.peekaboo2.helper.DatabaseManager;


public class MainActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), ContentBindActivity.class );

                //startPoint();


                databaseManager = DatabaseManager._Instance.initialization(getApplicationContext());

                Cursor cursor = databaseManager.selectDataForTable(DataBaseInfo._TableLastConnect);

                while(cursor.moveToNext())
                {
                    String nick = cursor.getString(0);
                    String address = cursor.getString(1);
                    String port = cursor.getString(2);


                    ConnectionInfo.ServerNick = nick;
                    ConnectionInfo.ServerHostName = address;
                    ConnectionInfo.ServerPort = port;
                }

                startActivity(intent);
                finish();
            }
        },2000);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
