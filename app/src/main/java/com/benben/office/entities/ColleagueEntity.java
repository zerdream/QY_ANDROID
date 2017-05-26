package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class ColleagueEntity {

    private String department ;
    private String account ;

    public ColleagueEntity(){}

    public ColleagueEntity(String department, String account) {
        super();
        this.department = department;
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
