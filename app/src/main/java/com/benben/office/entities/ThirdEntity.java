package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/25.
 */

public class ThirdEntity {
    private String systemName ;
    private String url ;

    public ThirdEntity(){}

    public ThirdEntity(String systemName, String url) {
        super();
        this.systemName = systemName;
        this.url = url;
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
}
