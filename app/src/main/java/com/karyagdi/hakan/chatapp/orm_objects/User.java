package com.karyagdi.hakan.chatapp.orm_objects;


import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hakan.karyagdi on 12.7.2017.
 */

public class User  implements Serializable {
    @DatabaseField(columnName = "USER_ID",id=true)
    private String id;
    @DatabaseField(columnName = "DISPLAY_NAME")
    private String displayName;

    public User()
    {

    }
    public User(String id, String displayName)
    {
        this.id = id;
        this.displayName = displayName;
    }
    public String getDisplayName()
    {
        return this.displayName;
    }
}
