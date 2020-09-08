package com.terukiss.peekaboo2.helper.Network;

import android.database.Cursor;

import com.terukiss.peekaboo2.helper.DataBase.DataBaseInfo;
import com.terukiss.peekaboo2.helper.DataBase.DatabaseManager;
import com.terukiss.peekaboo2.helper.JeongLog;

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
            // 방 데이터 베이스를 가져오는 명령
            // 명령문&*방이름|맥스인원|태크|패스워드|방장

            String[] refinedData = data[1].split("[*]");
            jeongLog.logD(refinedData.length+" 정제 데이터 길이 ");


            for(int i = 0 ; i < refinedData.length; i++)
            {

                String[] secondRefinedData = refinedData[i].split("[|]");

                if(secondRefinedData.length == 5)
                {
                    //*방이름|맥스인원|태크|패스워드|방장
                    jeongLog.logD("0. "+ secondRefinedData[0]);
                    jeongLog.logD("1. "+ secondRefinedData[1]);
                    jeongLog.logD("2. "+ secondRefinedData[2]);
                    jeongLog.logD("3. "+ secondRefinedData[3]);
                    jeongLog.logD("4. "+ secondRefinedData[4]);

                    DatabaseManager databaseManager = DatabaseManager._Instance;

                    String sql = "SELECT roomName from roomTBL WHERE serverAddress = '"+ConnectionInfo.ServerHostName+"'";
                    // 검색
                    Cursor cursor = databaseManager.selectData(sql);
                    Boolean DeduplicationCheck = false;

                    while(cursor.moveToNext())
                    {

                        if(secondRefinedData[0].equals(cursor.getString(0)))
                        {
                            DeduplicationCheck = true;
                            jeongLog.logD("지역 데이터 베이스에 이미 있는 방임 "+ true);
                        }
                    }

                    // 저장되어 있지 않다면
                    if(!DeduplicationCheck)
                    {
                        String[] insertData = new String[]{
                                secondRefinedData[0],
                                secondRefinedData[1],
                                secondRefinedData[2],
                                secondRefinedData[3],
                                secondRefinedData[4],
                                ConnectionInfo.ServerHostName
                        };
                        String[] fieldName = databaseManager.getColumnList(DataBaseInfo._TableRoom);
                        databaseManager.insertDataForDataDeduplication(DataBaseInfo._TableRoom, fieldName, insertData);
                    }

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
