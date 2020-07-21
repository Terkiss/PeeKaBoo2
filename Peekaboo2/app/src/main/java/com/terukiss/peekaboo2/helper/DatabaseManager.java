package com.terukiss.peekaboo2.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

@SuppressLint("Registered")
public class DatabaseManager extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static DatabaseManager _Instance = new DatabaseManager();

    private JeongLog jeongLog ;
    private DatabaseManager(){
       this.context = this;
    }

    // 데이터 베이스의 버전
    int version = 4;

    Context context;
    DatabaseHelper myDBHelper = null;
    SQLiteDatabase db;

    public DatabaseManager initialization(@NonNull Context context)
    {
        jeongLog = new JeongLog( "DB");


        this.context = context;
        myDBHelper = new DatabaseHelper(context);

            // 데이터 베이스 읽고 쓰기 가능 모드로 오픈
        db = myDBHelper.getWritableDatabase();

        return _Instance;
    }

    public void executeSQL(String SQL)
    {
        db.execSQL(SQL);
    }

    public  String[] getColumnList(String tblName)
    {
        String sql = "PRAGMA table_info("+tblName+");";
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<String> columns= new ArrayList<>();

        while(cursor.moveToNext())
        {

            String stt = cursor.getString(1);
            if( !stt.equals("_id"))
            {
                columns.add(stt);
            }

        }

        String[] returns = new String[columns.size()];

        for(int i = 0 ; i < columns.size(); i++)
        {
            returns[i] = columns.get(i);
            jeongLog.logD("컬럼명 :: "+returns[i]);
        }

        return returns;
    }


    public void insertData(String _TableName, String[] filedName, String[] data){
        String filed ="";
        String Riddle="(";

        for(int i = 0; i<filedName.length; i++)
        {
            if(i == filedName.length-1)
            {
                filed += filedName[i];
                Riddle +="?);";
                break;
            }
            filed += filedName[i]+", ";
            Riddle +="?, ";
        }
        String sql = "insert into "+_TableName+"("+filed+") values"+Riddle;
        jeongLog.logD(sql);
        db.execSQL(sql, data);
    }
    public void insertData(String table, String nullColumnHack, ContentValues values)
    {
        db.insert(table, nullColumnHack, values);
    }

    public void deleteData(String _TableName, String fieldName, String[] args)
    {
        String sql = "delete from "+_TableName+" where "+fieldName+" =?";
        db.execSQL(sql, args);
    }

    public void deleteData(String _TableName, String fieldName, String data)
    {
        String sql = "delete from "+_TableName+" where "+fieldName+"='"+data+"';";
        db.execSQL(sql);
    }

    public void deleteDataForTable(String _TableName)
    {
        String sql = "DELETE FROM "+_TableName+";";
        executeSQL(sql);
    }


    public Cursor selectData(String sql){
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }
    public Cursor selectData(String sql, String[] args){
        Cursor cursor = db.rawQuery(sql,args);
        return cursor;
    }
    public Cursor selectDataForTable(String tableName){
        String sql = "select * from "+tableName+";";
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }


    private class DatabaseHelper extends SQLiteOpenHelper
    {
        // 헬퍼 생성
        public DatabaseHelper(@Nullable Context context)
        {
            super(context, DataBaseInfo._DBName, null, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            jeongLog.logD("데이터 베이스 생성은 완료 ");
            jeongLog.logD("테이블 을 생성할 순서 ");

            jeongLog.logD("커넥션 리스트 테이블 생성");
            db.execSQL(DataBaseInfo._CreateConnectListTable);

            jeongLog.logD("마지막 연결 테이블 생성");
            db.execSQL(DataBaseInfo._CreateLastConnectTable);

            jeongLog.logD("유저 정보 테이블을 생성 ");
            db.execSQL(DataBaseInfo._CreateUserTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
        }
    }
}
