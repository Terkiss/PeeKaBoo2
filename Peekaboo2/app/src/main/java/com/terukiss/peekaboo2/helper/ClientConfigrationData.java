package com.terukiss.peekaboo2.helper;

import java.util.ArrayList;

public class ClientConfigrationData {

    private static ArrayList<String> roomData = new ArrayList<>();

    public static ArrayList<String> getRoomData() {
        return roomData;
    }

    public static void setRoomData(String[] roomData) {

        ClientConfigrationData.roomData.clear();
        for (int i = 1; i < roomData.length; i++) {
            ClientConfigrationData.roomData.add(roomData[i]);

        }

    }


    // 룸 데이터 모델 추후에 수정 하기로
    //Recent Chat Data
    private class RoomDataModel
    {
        private String roomData ;
        private String recentSender;
        private String recentContents;
        private String recentTime;
        RoomDataModel()
        {

        }

        public String getRoomData() {
            return roomData;
        }

        public void setRoomData(String roomData) {
            this.roomData = roomData;
        }

        public String getRecentSender() {
            return recentSender;
        }

        public void setRecentSender(String recentSender) {
            this.recentSender = recentSender;
        }

        public String getRecentContents() {
            return recentContents;
        }

        public void setRecentContents(String recentContents) {
            this.recentContents = recentContents;
        }

        public String getRecentTime() {
            return recentTime;
        }

        public void setRecentTime(String recentTime) {
            this.recentTime = recentTime;
        }
    }
}
