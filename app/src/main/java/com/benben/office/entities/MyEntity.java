package com.benben.office.entities;

/**
 * Created by 牛海丰 on 2017/5/23.
 */

public class MyEntity {
    private String userPic ;
    private String name ;
    private String nickName ;
    private String companyName ;
    private String address ;
    private String email ;
    private String phoneNumber ;
    private String department ;
    private String picCode ;

    public MyEntity(){}

    public MyEntity(String userPic, String name, String nickName, String companyName, String address, String email, String phoneNumber, String department, String picCode) {
        super();
        this.userPic = userPic;
        this.name = name;
        this.nickName = nickName;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.picCode = picCode;
    }

    public String getUserPic() {

        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }
}
