package com.karyagdi.hakan.chatapp.orm_objects;


import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by hakan on 7/9/17.
 */

public class Message extends SugarRecord<Message> {
    private String messageId;
    private String chatId;
    private String uId;
    private String messageText;
    private long messageTime;


    public Message(String uId, String messageText, Long messageTime) {
        this.messageText = messageText;
        this.uId = uId;
        this.messageTime = messageTime;//new Date().getTime();
    }

    public Message() {
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

    public void setuID(String uId) {
        this.uId = uId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
