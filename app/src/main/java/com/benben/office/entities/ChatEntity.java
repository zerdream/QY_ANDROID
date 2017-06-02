package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class ChatEntity {

    private String typeId ;
    private String Title ;
    private String message ;
    private String updateTime ;

    public ChatEntity(){}

    public ChatEntity(String typeId, String title, String message, String updateTime) {
        super();
        this.typeId = typeId;
        Title = title;
        this.message = message;
        this.updateTime = updateTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
