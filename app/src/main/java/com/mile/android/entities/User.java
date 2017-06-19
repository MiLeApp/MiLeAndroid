package com.mile.android.entities;

import android.content.Intent;

import io.realm.RealmObject;

/**
 * Created by yacovyitzhak on 17/06/2017.
 */

public class User {

    private Integer  UserId;
    private String IdFB;
    private String PhoneNum;
    private String NickName;
    private Integer Mileage;
    private String Address;
    private Integer Role;

    public User(String idFB, String phoneNum, String nickName) {
        IdFB = idFB;
        PhoneNum = phoneNum;
        NickName = nickName;
    }

    public User(Integer userId, String idFB, String phoneNum, String nickName, Integer mileage, String address, Integer role) {
        UserId = userId;
        IdFB = idFB;
        PhoneNum = phoneNum;
        NickName = nickName;
        Mileage = mileage;
        Address = address;
        Role = role;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getIdFB() {
        return IdFB;
    }

    public void setIdFB(String idFB) {
        IdFB = idFB;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Integer getMileage() {
        return Mileage;
    }

    public void setMileage(Integer mileage) {
        Mileage = mileage;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer role) {
        Role = role;
    }
}
