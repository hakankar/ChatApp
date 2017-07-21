package com.karyagdi.hakan.chatapp.Utility;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.j256.ormlite.dao.Dao;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.ChatUser;
import com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper;
import com.karyagdi.hakan.chatapp.orm_objects.Message;
import com.karyagdi.hakan.chatapp.orm_objects.User;

import java.sql.SQLException;

/**
 * Created by hakan.karyagdi on 20.7.2017.
 */

public class BaseService extends Service {
    private Context context;
    private com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper DatabaseHelper;
    private Dao<Chat,Integer> mChat;
    private Dao<ChatUser,Integer> mChatUser;
    private Dao<Message,Integer> mMessage;
    private Dao<User,Integer> mUser;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DatabaseHelper getDatabaseHelper() {
        if(DatabaseHelper==null)
            DatabaseHelper=new DatabaseHelper(getApplicationContext());
        return DatabaseHelper;
    }

    public Dao<Chat, Integer> getmChat() {
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

    public Dao<ChatUser, Integer> getmChatUser() {
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

    public Dao<Message, Integer> getmMessage() {
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

    public Dao<User, Integer> getmUser() {
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
