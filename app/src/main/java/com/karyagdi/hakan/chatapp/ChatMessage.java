package com.karyagdi.hakan.chatapp;

import java.util.Date;

/**
 * Created by hakan on 7/9/17.
 */

public class ChatMessage {
    private String uID;
    private String messageText;
    private String messageUser;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser, String uID, Long messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.uID = uID;
        this.messageTime = messageTime;//new Date().getTime();
    }

    public ChatMessage() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
