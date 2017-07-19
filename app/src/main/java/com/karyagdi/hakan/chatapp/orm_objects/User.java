package com.karyagdi.hakan.chatapp.orm_objects;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class User  implements Serializable {
    @DatabaseField(columnName = "USER_ID")
    private String id;
    @DatabaseField(columnName = "FRIEND_ID")
    private String friend;

    public User()
    {

    }
    /*public ArrayList<User> getFriends(int index) {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }*/
}
