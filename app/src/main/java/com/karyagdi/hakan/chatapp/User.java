package com.karyagdi.hakan.chatapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class User {
    private String uID;
    private ArrayList<User> friends;

    public User()
    {
        friends=new ArrayList<User>();
    }
    public ArrayList<User> getFriends(int index) {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }
}
