package com.karyagdi.hakan.chatapp;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by hakan on 7/9/17.
 */
@IgnoreExtraProperties
public class MessageTemplate {
    private String uId;
    private String messageText;
    private long messageTime;

    public MessageTemplate(String messageText, String messageUser, String uId, Long messageTime) {
        this.messageText = messageText;
        this.uId = uId;
        this.messageTime = messageTime;//new Date().getTime();
    }

    public MessageTemplate() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }



    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getuID() {
        return uId;
    }

    public void setuID(String uID) {
        this.uId = uId;
    }
}
