package com.terukiss.peekaboo2.helper.DataBase;

import java.util.ArrayList;


public class DataBaseInfo {
    public      static      final       String      _DBName             =   "peekaboo2.db"      ;

    public      static      final       String      _TableConnectList   =   "connectListTBL"    ;
    public      static      final       String      _TableLastConnect   =   "lastConnectTBL"    ;

    public      static      final       String      _TableUser          =   "userTBL"           ;

    // 방관련 테이블

    public      static      final       String      _TableRoom          =   "roomTBL"           ;
    public      static      final       String      _TableChat          =   "chatTBL"           ;

    public      static      final       String      _TableIcon          =   "iconTable"         ;
    // sql 크리에이터 문


    public static final String _CreateConnectListTable = "Create table if not exists connectListTBL(_id INTEGER, hostNick varchar(60), hostAddress varchar(60), port varchar(30), PRIMARY KEY(\"_id\" AUTOINCREMENT))";
    public static final String _CreateLastConnectTable = "Create table if not exists lastConnectTBL(hostNick varchar(60), hostAddress varchar(60), port varchar(60))";
    public static final String _CreateUserTable = "Create table if not exists userTBL(nickName varchar(60), uuid varchar(100), profilePicture varchar(100))";
    public static final String _CreateRoomTable = "Create table if not exists roomTBL(_id integer, roomName varchar(100), maxJoin varchar(60), roomTag varchar(200), joinPassword varchar(100), superUser varchar(100), serverAddress varchar(100), PRIMARY KEY(_id AUTOINCREMENT))";
    public static final String _CreateChatTable = "CREATE TABLE IF NOT EXISTS chatTBL(_id	INTEGER, roomName	varchar(100), sender	varchar(100), comment	varchar(200), time	varchar(10), PRIMARY KEY(_id AUTOINCREMENT))";
}
