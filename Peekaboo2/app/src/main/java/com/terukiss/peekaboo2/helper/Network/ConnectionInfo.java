package com.terukiss.peekaboo2.helper.Network;

// 커넥션 관련 클래스
public class ConnectionInfo {
    public static String ServerNick = "";
    public static String ServerHostName ="" ;
    public static String ServerPort ="";

    public static void CLEAN()
    {
        ServerNick = "";
        ServerHostName = "";
        ServerPort = "";
    }
}
