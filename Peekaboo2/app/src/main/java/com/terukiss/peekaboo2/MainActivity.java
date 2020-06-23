package com.terukiss.peekaboo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.terukiss.peekaboo2.activity.ContentBindActivity;
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
