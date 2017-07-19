package com.karyagdi.hakan.chatapp.orm_objects;

import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class Chat implements Serializable {
    @DatabaseField(columnName = "ID",id=true)
    private String id;

    public Chat()
    {


    }
    public Chat(String id)
    {
        this.id=id;

    }
    /*public List<ChatUser> getUsers() {
        return ChatUser.find(ChatUser.class,"chat=?",this.id);
    }

    public void addUser(String uId) {
        ChatUser chatUser=new ChatUser(this.id,uId);
        chatUser.save();
    }

    public List<Message> getMessages() {
        return Message.find(Message.class,"chat=?",this.id);
    }

    public Message getMessagesByKey(String messageId) {
        List<Message> result = Message.find(Message.class,"chat=? AND id=?",this.id,messageId);
       if(result.size()>0)
       {
           return result.get(0);
       }else
       {
           return null;
       }
    }

    public boolean containsMessage(String messageId) {
        Log.v("degerler: ",messageId + " -- " +this.id);
        List<Message> result = Message.find(Message.class,"chat=? AND id=?",this.id,messageId);
        if(result.size()>0)
        {
            return true;
        }else
        {
            return false;
        }
    }


    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }*/
}
