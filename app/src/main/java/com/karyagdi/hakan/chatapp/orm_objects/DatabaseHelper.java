package com.karyagdi.hakan.chatapp.orm_objects;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by hakan.karyagdi on 19.7.2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "chatapp.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<Chat, Integer> mChat = null;
    private RuntimeExceptionDao<Chat, Integer> mChatRuntimeExceptionDao = null;

    private Dao<ChatUser, Integer> mChatUser = null;
    private RuntimeExceptionDao<ChatUser, Integer> mChatUserRuntimeExceptionDao = null;

    private Dao<Message, Integer> mMessage = null;
    private RuntimeExceptionDao<Message, Integer> mMessageRuntimeExceptionDao = null;

    private Dao<User, Integer> mUser = null;
    private RuntimeExceptionDao<User, Integer> mUserRuntimeExceptionDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Chat.class);
            TableUtils.createTable(connectionSource,ChatUser.class);
            TableUtils.createTable(connectionSource,Message.class);
            TableUtils.createTable(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Chat.class,true);
            TableUtils.dropTable(connectionSource,ChatUser.class,true);
            TableUtils.dropTable(connectionSource,Message.class,true);
            TableUtils.dropTable(connectionSource,User.class,true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Chat, Integer> getmChat() throws SQLException, java.sql.SQLException {
        if(mChat==null)
        {
            mChat=getDao(Chat.class);
        }
        return mChat;
    }

    public Dao<ChatUser, Integer> getmChatUser() throws SQLException, java.sql.SQLException{
        if(mChatUser==null)
        {
            mChatUser=getDao(ChatUser.class);
        }
        return mChatUser;
    }

    public Dao<Message, Integer> getmMessage() throws SQLException, java.sql.SQLException{
        if(mMessage==null)
        {
            mMessage=getDao(Message.class);
        }
        return mMessage;
    }

    public Dao<User, Integer> getmUser()throws SQLException, java.sql.SQLException {
        if(mUser==null)
        {
            mUser=getDao(User.class);
        }
        return mUser;
    }

    public RuntimeExceptionDao<Chat, Integer> getmChatRuntimeExceptionDao() {
        if(mChatRuntimeExceptionDao==null)
        {
            mChatRuntimeExceptionDao=getRuntimeExceptionDao(Chat.class);
        }
        return mChatRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<ChatUser, Integer> getmChatUserRuntimeExceptionDao() {
        if(mChatUserRuntimeExceptionDao==null)
        {
            mChatUserRuntimeExceptionDao=getRuntimeExceptionDao(ChatUser.class);
        }
        return mChatUserRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<Message, Integer> getmMessageRuntimeExceptionDao() {
        if(mMessageRuntimeExceptionDao==null)
        {
            mMessageRuntimeExceptionDao=getRuntimeExceptionDao(Message.class);
        }
        return mMessageRuntimeExceptionDao;
    }

    public RuntimeExceptionDao<User, Integer> getmUserRuntimeExceptionDao() {
        if(mUserRuntimeExceptionDao==null)
        {
            mUserRuntimeExceptionDao=getRuntimeExceptionDao(User.class);
        }
        return mUserRuntimeExceptionDao;
    }
    @Override
    public void close() {
        mChat = null;
        mChatUser = null;
        mMessage = null;
        mUser = null;
        super.close();
    }



}
