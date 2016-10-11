package com.otz.SCUchat.bean;

import java.util.Date;

/**
 * Created by Lifen on 3/17/16.
 */
public class myMessage {
    private String mText;
    private String mSender;
    private Date mDate;

    public myMessage(){

    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }
}
