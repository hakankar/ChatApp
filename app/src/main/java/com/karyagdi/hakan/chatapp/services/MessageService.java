package com.karyagdi.hakan.chatapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RelativeLayout;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.karyagdi.hakan.chatapp.MessageTemplate;
import com.karyagdi.hakan.chatapp.R;
import com.karyagdi.hakan.chatapp.Utility.BaseService;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper;
import com.karyagdi.hakan.chatapp.orm_objects.Message;

import java.sql.SQLException;
import java.util.Date;

public class MessageService extends BaseService {
    DatabaseHelper DatabaseHelper;
    String chatId;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mFirebaseAuth;
    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        //        DatabaseHelper=new DatabaseHelper(getApplicationContext());
        Log.v("Service message: ","Running");
        String userId ="vjvT7az7egfhBNcQkA2MRXJA4xf2";
        Query referance = mFirebaseDatabase.getReference().child("chats").orderByChild("users/vjvT7az7egfhBNcQkA2MRXJA4xf2").equalTo(true);
        referance.addChildEventListener(new ChildEventListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                try {

                    /*MessageTemplate newMessage = dataSnapshot.getValue(MessageTemplate.class);
                    Message message = new Message(newMessage.getsender(),newMessage.getmessage(),newMessage.getdate());
                    message.setchat(chatId);
                    message.setid(dataSnapshot.getKey());
                    getmMessage().createIfNotExists(message);*/
                    System.out.println("Added" + dataSnapshot.getValue());

//                }
                /*catch (SQLException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("changed" + dataSnapshot.getValue());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v("Removed ID: ",dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("HATA:  ",databaseError.getMessage());
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        Intent restartIntent = new Intent(getApplicationContext(),this.getClass());
//        restartIntent.setPackage(getPackageName());
//        startService(restartIntent);
        super.onTaskRemoved(rootIntent);
        Log.v("Service message: ","Close");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("Service message: ","Close");
    }
}
