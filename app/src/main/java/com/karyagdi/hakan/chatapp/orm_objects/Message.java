package com.karyagdi.hakan.chatapp.orm_objects;


import com.orm.SugarRecord;


/**
 * Created by hakan on 7/9/17.
 */

public class Message extends SugarRecord<Message> {
    private String id;
    private String chat;
    private String sender;
    private String message;
    private long date;


    public Message(String sender, String message, Long date) {
        this.message = message;
        this.sender = sender;
        this.date = date;//new Date().getTime();
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
