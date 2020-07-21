package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;

public class ClientConfigrationData  {



    private static ArrayList<String> roomData = new ArrayList<>();

    public static ArrayList<String> getRoomData() {
        return roomData;
    }

    public static void setRoomData(String[] roomData) {

        ClientConfigrationData.roomData.clear();
        for(int i = 1; i < roomData.length; i++)
        {
            ClientConfigrationData.roomData.add(roomData[i]);

        }

    }
}
