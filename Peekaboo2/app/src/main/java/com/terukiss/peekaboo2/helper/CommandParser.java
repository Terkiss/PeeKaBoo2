package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;

public class CommandParser {

    public static void Parser(String str) {
        //https://copycoding.tistory.com/289
        JeongLog jeongLog = new JeongLog("Communication");
        String[] data = str.split("[|]");

        if (data.length < 2) {
            jeongLog.logD("받은 데이터가 이상 합니다.");
        }

        int command = Integer.parseInt(data[0]);

        if (command == PeeKaBooProtocol.RoomCreate)
        {
           // jeongLog.logD(data[1]);
        }
        else if (command == PeeKaBooProtocol.MesaageSEND)
        {

        }
        else if( command == PeeKaBooProtocol.RoomDelete )
        {

        }
        else if( command == PeeKaBooProtocol.RoomRequst )
        {
            ClientConfigrationData.setRoomData(data);

            ArrayList<String> dsss = ClientConfigrationData.getRoomData();
            for(int i = 0; i<dsss.size(); i++)
            {
                jeongLog.logD("저장된 값 ? "+dsss.get(i));
            }
        }







    }
}
