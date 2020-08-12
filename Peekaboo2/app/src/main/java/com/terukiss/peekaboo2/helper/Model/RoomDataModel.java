package com.terukiss.peekaboo2.helper.Model;

public class RoomDataModel {
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
