package com.karyagdi.hakan.chatapp.orm_objects;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class Chat extends SugarRecord<Chat>{
    private String chatId;

    public Chat()
    {


    }
    public Chat(String chatId)
    {
        this.chatId=chatId;

    }
    public List<ChatUser> getUsers() {
        return ChatUser.find(ChatUser.class,"chat_id=?",this.chatId);
    }

    public void addUser(String uId) {
        ChatUser chatUser=new ChatUser(this.chatId,uId);
        chatUser.save();
    }

    public List<Message> getMessages() {
        return Message.find(Message.class,"chat_id=?",this.chatId);
    }

    public Message getMessagesByKey(String messageId) {
        List<Message> result = Message.find(Message.class,"chat_id=? AND message_id=?",this.chatId,messageId);
       if(result.size()>0)
       {
           return result.get(0);
       }else
       {
           return null;
       }
    }

    public boolean containsMessage(String messageId) {
        List<Message> result = Message.find(Message.class,"chat_id=? AND message_id=?",this.chatId,messageId);
        if(result.size()>0)
        {
            return true;
        }else
        {
            return false;
        }
    }


    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
