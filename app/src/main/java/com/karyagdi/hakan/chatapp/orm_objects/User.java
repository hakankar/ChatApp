package com.karyagdi.hakan.chatapp.orm_objects;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class User extends SugarRecord<User> {
    private String uId;
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
