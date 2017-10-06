package com.karyagdi.hakan.chatapp.Utility;

/**
 * Created by hakan.karyagdi on 6.10.2017.
 */

public class MyProperties {
    private static MyProperties mInstance= null;

    public String currentChatId;


    protected MyProperties(){}

    public static synchronized MyProperties getInstance(){
        if(null == mInstance){
            mInstance = new MyProperties();
        }
        return mInstance;
    }
}