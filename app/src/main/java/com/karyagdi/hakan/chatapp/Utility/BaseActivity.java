package com.karyagdi.hakan.chatapp.Utility;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.dao.Dao;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.Message;
import com.karyagdi.hakan.chatapp.orm_objects.ChatUser;
import com.karyagdi.hakan.chatapp.orm_objects.User;
import com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper;

import java.sql.SQLException;

/**
 * Created by hakan.karyagdi on 19.7.2017.
 */

public class BaseActivity extends AppCompatActivity {
    private DatabaseHelper DatabaseHelper;
    private Dao<Chat,Integer> mChat;
    private Dao<ChatUser,Integer> mChatUser;
    private Dao<Message,Integer> mMessage;
    private Dao<User,Integer> mUser;


    public DatabaseHelper getDatabaseHelper() {
        if(DatabaseHelper==null)
            DatabaseHelper=new DatabaseHelper(getApplicationContext());
        return DatabaseHelper;
    }

    public Dao<Chat, Integer> getmChat(DatabaseHelper DatabaseHelper) {
        getDatabaseHelper();
        if(mChat==null) {
            try {
                mChat = DatabaseHelper.getmChat();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mChat;
    }

    public Dao<ChatUser, Integer> getmChatUser(DatabaseHelper DatabaseHelper) {
        getDatabaseHelper();
        if(mChatUser==null) {
            try {
                mChatUser = DatabaseHelper.getmChatUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mChatUser;
    }

    public Dao<Message, Integer> getmMessage(DatabaseHelper DatabaseHelper) {
        getDatabaseHelper();
        if(mMessage==null) {
            try {
                mMessage = DatabaseHelper.getmMessage();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mMessage;
    }

    public Dao<User, Integer> getmUser(DatabaseHelper DatabaseHelper) {
        getDatabaseHelper();
        if(mUser==null) {
            try {
                mUser = DatabaseHelper.getmUser();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mUser;
    }
}
