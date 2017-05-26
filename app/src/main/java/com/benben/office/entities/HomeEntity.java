package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/24.
 */

public class HomeEntity {
    private String systemName ;
    private String url ;
    private String systemId ;
    private String count ;
    private String title ;
    private String type ;
    private String time ;

    public HomeEntity (){}

    public HomeEntity(String systemName, String url, String systemId, String count, String title, String type, String time) {
        super();
        this.systemName = systemName;
        this.url = url;
        this.systemId = systemId;
        this.count = count;
        this.title = title;
        this.type = type;
        this.time = time;
    }

    public String getSystemName() {

        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
