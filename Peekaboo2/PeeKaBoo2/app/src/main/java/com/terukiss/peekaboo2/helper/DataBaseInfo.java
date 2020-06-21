package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;


public class DataBaseInfo {
    public      static      final       String      _DBName             =   "peekaboo2.db"      ;
    public      static      final       String      _TableIcon          =   "iconTable"         ;
    public      static      final       String      _TableConnectList   =   "connectListTBL"    ;
    public      static      final       String      _TableLastConnect   =   "lastConnectTBL"    ;
    public      static      final       String      _TableUser          =   "userTBL"           ;

    // sql 크리에이터 문


    public static final String _CreateConnectListTable = "Create table connectListTBL(_id INTEGER, hostName varchar(30), hostAddress varchar(30), port varchar(30), PRIMARY KEY(\"_id\" AUTOINCREMENT))";
    public static final String _CreateLastConnectTable = "Create table lastConnectTBL(hostName varchar(30), hostAddress varchar(30), port varchar(30))";
    public static final String _CreateUserTable = "Create table userTBL(uuid varchar(60), picturePath varchar(100))";


}
