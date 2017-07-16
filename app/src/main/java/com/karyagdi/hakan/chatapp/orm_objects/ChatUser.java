package com.karyagdi.hakan.chatapp.orm_objects;

import com.orm.SugarRecord;

/**
 * Created by hakan on 7/16/17.
 */

public class ChatUser extends SugarRecord<ChatUser> {
    private String chatId;
    private String uId;

    public ChatUser (String chatId, String uId)
    {
        this.chatId=chatId;
        this.uId=uId;
    }
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
