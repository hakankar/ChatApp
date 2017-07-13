package com.karyagdi.hakan.chatapp;

import java.util.ArrayList;


/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class Chat {
    private ArrayList<String> Users;
    private ArrayList<ChatMessage> Messages;

    public Chat()
    {
        Users=new ArrayList<String>();
        Messages=new ArrayList<ChatMessage>();
    }
    public ArrayList<String> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<String> Users) {
        this.Users = Users;
    }

    public void addUsers(String uID) {
        Users.add(uID);
    }


    public ArrayList<ChatMessage> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<ChatMessage> Messages) {
        this.Messages = Messages;
    }

    public void addMessages(ChatMessage message) {
        Messages.add(message);
    }



}
