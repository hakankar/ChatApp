package com.karyagdi.hakan.chatapp.orm_objects;

import com.orm.SugarRecord;

/**
 * Created by hakan on 7/16/17.
 */

public class ChatUser extends SugarRecord<ChatUser> {
    private String chat;
    private String user;

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
