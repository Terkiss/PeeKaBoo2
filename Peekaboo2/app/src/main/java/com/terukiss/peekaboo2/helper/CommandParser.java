package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;

public class CommandParser {

    public static void Parser(String str) {
        //https://copycoding.tistory.com/289
        JeongLog jeongLog = new JeongLog("Communication");
        String[] data = str.split("&");
        jeongLog.logD(data[0]);
        jeongLog.logD(data[1]);
        jeongLog.logD("아니 왜 안대는데");
        if (data.length < 1) {
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
        else if( command == PeeKaBooProtocol.ROOMREQUESTRESPONSE )
        {
            String[] refinedData = data[1].split("[*]");
            jeongLog.logD(refinedData.length+" 정제 데이터 길이 ");
            for(int i = 0 ; i < refinedData.length; i++)
            {

                String[] secondRefinedData = refinedData[i].split("[|]");

                if(secondRefinedData.length == 5)
                {
                    jeongLog.logD("0. "+ secondRefinedData[0]);
                    jeongLog.logD("1. "+ secondRefinedData[1]);
                    jeongLog.logD("2. "+ secondRefinedData[2]);
                    jeongLog.logD("3. "+ secondRefinedData[3]);
                    jeongLog.logD("4. "+ secondRefinedData[4]);

                    DatabaseManager databaseManager = DatabaseManager._Instance;

                }
            }

           // jeongLog.logD(refinedData[0]+"처음 리파인 데이터");



//            ClientConfigrationData.setRoomData(data);
//
//            ArrayList<String> dsss = ClientConfigrationData.getRoomData();
//            for(int i = 0; i<dsss.size(); i++)
//            {
//                jeongLog.logD("저장된 값 ? "+dsss.get(i));
//            }
            // 데이터 베이스에 저장
        }







    }
}
