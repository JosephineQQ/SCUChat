package com.otz.SCUchat.bean;

/**
 * Created by OTZ on 2/24/2016.
 */
public class UserBean {
    //public String UID;
    public String username;
    public String password;
    public String iconPath;

    public UserBean(String username, String password, String iconPath) {  //String UID,
        //this.UID = UID;
        this.username = username;
        this.password = password;
        this.iconPath = iconPath;
    }

    public UserBean() {
        super();
    }

    /*public String getUID() {
        return UID;
    }*/

    /*public void setUID(String UID) {
        this.UID = UID;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
