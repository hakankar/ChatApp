package com.karyagdi.hakan.chatapp.orm_objects;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by hakan on 7/9/17.
 */

public class Message implements Serializable {
    @DatabaseField(columnName = "ID",id=true)
    private String id;
    @DatabaseField(columnName = "CHAT_ID")
    private String chat;
    @DatabaseField(columnName = "SENDER_ID")
    private String sender;
    @DatabaseField(columnName = "MESSAGE")
    private String message;
    @DatabaseField(columnName = "DATE")
    private long date;


    public Message(String sender, String message, Long date,String chat) {
        this.message = message;
        this.sender = sender;
        this.date = date;//new Date().getTime();
        this.chat=chat;
    }

    public Message() {
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

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getchat() {
        return chat;
    }

    public void setchat(String chat) {
        this.chat = chat;
    }
}
