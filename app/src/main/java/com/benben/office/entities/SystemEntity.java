package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class SystemEntity {
    private String systemName ;
    private String url ;
    private String systemId ;
    private String label ;

    public SystemEntity(){}

    public SystemEntity(String systemName, String url, String systemId, String label) {
        super();
        this.systemName = systemName;
        this.url = url;
        this.systemId = systemId;
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
