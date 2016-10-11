package com.otz.SCUchat.bean;

/**
 * Created by Lifen on 3/17/16.
 */
public class MsgBean {
    //public String sender;
    //public String receiver;
    public String msgText;
    public int msgType;    // send or receiver
    //public Date msgTime;


    public MsgBean(String msgContent, int msgSendOrRecive) {  //String UID,
        this.msgText = msgContent;
        this.msgType = msgSendOrRecive;
    }

}
