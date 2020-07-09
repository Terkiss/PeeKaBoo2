package com.terukiss.peekaboo2.activity;

public class UserProfile {
    private static String profileNickName;
    private static String profilePicturePath;
    private static String profileUUID;

    public static String getProfileNickName() {
        return profileNickName;
    }

    public static void setProfileNickName(String profileNickName) {
        UserProfile.profileNickName = profileNickName;
    }

    public static String getProfilePicturePath() {
        return profilePicturePath;
    }

    public static void setProfilePicturePath(String profilePicturePath) {
        UserProfile.profilePicturePath = profilePicturePath;
    }

    public static String getProfileUUID() {
        return profileUUID;
    }

    public static void setProfileUUID(String profileUUID) {
        UserProfile.profileUUID = profileUUID;
    }
}
