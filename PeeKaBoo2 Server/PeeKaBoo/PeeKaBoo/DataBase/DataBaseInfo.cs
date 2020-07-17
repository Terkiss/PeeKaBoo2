using System;
using System.Collections.Generic;
using System.Text;

namespace PeeKaBoo.DataBase
{
    class DataBaseInfo
    {

        public static string CreateServerConfigData = "CREATE TABLE IF NOT EXISTS serverConfigData(serverName varchar(100), port varchar(10), disclosureStatus varchar(100), serverIntroduction varchar(200))";

        public static  string CreateRoomTable = "CREATE TABLE IF NOT EXISTS roomTBL(roomName varchar(100), maxJoin varchar(60), roomTag varchar(200), joinPassword varchar(100), superUser varchar(100))";

        public static string CreateChatTable = "CREATE TABLE IF NOT EXISTS chatTBL(_id	INTEGER, roomName	varchar(100), sender	varchar(100), comment	varchar(200), time	varchar(10), PRIMARY KEY(_id AUTOINCREMENT))";

        public static string CreateRoomJoinUserTable = "CREATE TABLE IF NOT EXISTS roomJoinUserTBL(_id INTEGER, roomName varchar(100), joinTime varchar(10), user varchar(100))";

        public static string CreateServerLogTable = "Create table if not exists serverlog(time varchar(100), message varchar(1000))";


        public static string _DBName = "koko.db";

        public static string _TableRoom = "roomTBL";
        public static string _TableChat = "chatTBL";
        public static string _TableRoomJoinUser = "roomJoinUserTBL";
        public static string _TableServerConfigData = "serverConfigData";
        public static string _TableServerLog = "serverlog";
    }
}
