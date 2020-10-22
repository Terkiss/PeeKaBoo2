package com.terukiss.peekaboo2.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class JeongLog  {
    //public static JeongLog log;
    private Context context;
    private String tag = "JM";



    public JeongLog(String tags) {
        this.tag = tags;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }



    public void toast(String str)
    {

        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }



    public void logD(String str)
    {
        Log.d(tag, str);
    }

    public void logLongD(String str)
    {
        Log.d(tag, "계속 호출됨" + str.length());
        while( str.length() > 0 )
        {
            if( str.length() > 2000 )
            {
                Log.d(tag, "롱롱"+str.substring( 0, 2000 ));
                str = str.substring( 2000 );
            }
            else
            {
                Log.d(tag, str);
            }
        }
    }



}
