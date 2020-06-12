package com.terukiss.peekaboo2.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class JeongLog {
    //public static JeongLog log;
    private Context context;
    private String tag = "JM";

    public JeongLog(Context _context, String tag) {
        context = _context;
        this.tag = tag;
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
        while( str.length() > 0 )
        {
            if( str.length() > 4000 )
            {
                Log.d(tag, str.substring( 0, 4000 ));
                str = str.substring( 4000 );
            }
            else
            {
                Log.d(tag, str);
            }
        }
    }
}
