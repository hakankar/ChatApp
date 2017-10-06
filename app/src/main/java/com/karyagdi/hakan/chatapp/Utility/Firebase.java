package com.karyagdi.hakan.chatapp.Utility;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hakan.karyagdi on 6.10.2017.
 */


public class Firebase {
    private static FirebaseAuth mAuthInstance= null;
    private static FirebaseDatabase mDatabaseInstance= null;




    protected Firebase(){}

    public static synchronized FirebaseAuth getAuthInstance(){
        if(null == mAuthInstance){
            mAuthInstance =  FirebaseAuth.getInstance();
        }
        return mAuthInstance;
    }

    public static synchronized FirebaseDatabase getDatebaseInstance(){
        if(null == mDatabaseInstance){
            mDatabaseInstance =  FirebaseDatabase.getInstance();
        }
        return mDatabaseInstance;
    }
}