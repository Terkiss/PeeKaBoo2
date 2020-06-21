package com.terukiss.peekaboo2.activity;

public class ServerListItem extends Item
{
    public ServerListItem()
    {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSmallIconPath() {
        return smallIconPath;
    }

    public void setSmallIconPath(String smallIconPath) {
        this.smallIconPath = smallIconPath;
    }

    public String getLargeIconPath() {
        return largeIconPath;
    }

    public void setLargeIconPath(String largeIconPath) {
        this.largeIconPath = largeIconPath;
    }

    public String getPakageName() {
        return pakageName;
    }

    public void setPakageName(String pakageName) {
        this.pakageName = pakageName;
    }
}
