package com.tp.gamemanagementsystem.models;

public class User {

    Integer userID;
    String userName;
    UserList list;



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserList getList() {
        return list;
    }

    public void setList(UserList list) {
        this.list = list;
    }

}
