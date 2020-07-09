package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;


public class DataBaseInfo {
    public      static      final       String      _DBName             =   "peekaboo2.db"      ;

    public      static      final       String      _TableConnectList   =   "connectListTBL"    ;
    public      static      final       String      _TableLastConnect   =   "lastConnectTBL"    ;

    public      static      final       String      _TableUser          =   "userTBL"           ;
    public      static      final       String      _TableIcon          =   "iconTable"         ;
    // sql 크리에이터 문


    public static final String _CreateConnectListTable = "Create table connectListTBL(_id INTEGER, hostNick varchar(60), hostAddress varchar(60), port varchar(30), PRIMARY KEY(\"_id\" AUTOINCREMENT))";
    public static final String _CreateLastConnectTable = "Create table lastConnectTBL(hostNick varchar(60), hostAddress varchar(60), port varchar(60))";
    public static final String _CreateUserTable = "Create table userTBL(nickName varchar(60), uuid varchar(100), profilePicture varchar(100))";


}
