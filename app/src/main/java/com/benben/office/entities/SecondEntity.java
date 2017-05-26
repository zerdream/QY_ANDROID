package com.benben.office.entities;

import java.util.List;

/**
 * Created by 牛海丰 on 2017/5/24.
 */

public class SecondEntity {
    private String systemName ;
    private List<ThirdEntity> child ;

    public SecondEntity(){}

    public SecondEntity(String systemName ) {
        super();
        this.systemName = systemName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<ThirdEntity> getChild() {
        return child;
    }

    public void setChild(List<ThirdEntity> list) {
        this.child = list;
    }
}
