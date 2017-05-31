package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class ChatEntity {

    private String functionName ;

    public ChatEntity(){}

    public ChatEntity(String functionName) {
        super();
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

}
