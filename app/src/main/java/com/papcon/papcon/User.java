package com.papcon.papcon;

/**
 * Created by qkrdb on 2017-07-10.
 */

public class User {
    String usrID;
    String userPassword;
    String userName;
    String userAge;

    public User(String usrID, String userPassword, String userName, String userAge) {
        this.usrID = usrID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUsrID() {
        return usrID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUsrID(String usrID) {
        this.usrID = usrID;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
