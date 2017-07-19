package com.karyagdi.hakan.chatapp.orm_objects;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by hakan on 7/16/17.
 */

public class ChatUser  implements Serializable {
    @DatabaseField(columnName = "CHAT_ID")
    private String chat;
    @DatabaseField(columnName = "USER_ID")
    private String user;

    public  ChatUser(){

    }
    public ChatUser (String chat, String user)
    {
        this.chat=chat;
        this.user=user;
    }
    public String getchat() {
        return chat;
    }

    public void setchat(String chat) {
        this.chat = chat;
    }

    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }
}
