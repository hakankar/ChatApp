package com.karyagdi.hakan.chatapp;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


import java.util.Date;

/**
 * Created by hakan on 7/9/17.
 */
@IgnoreExtraProperties
public class MessageTemplate {
    private String sender;
    private String message;
    private long date;

    public MessageTemplate(String message, String messageUser, String sender, Long date) {
        this.message = message;
        this.sender = sender;
        this.date = date;//new Date().getTime();
    }

    public MessageTemplate() {
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }



    public long getdate() {
        return date;
    }

    public void setdate(long date) {
        this.date = date;
    }

    public String getsender() {
        return sender;
    }

    public void setsender(String sender) {
        this.sender = sender;
    }
}
