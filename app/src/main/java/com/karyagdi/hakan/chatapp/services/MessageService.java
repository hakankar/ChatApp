package com.karyagdi.hakan.chatapp.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
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
import com.karyagdi.hakan.chatapp.orm_objects.User;

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
        String userId ="AtUYqKbRS4NQ0SVClZvyAr2cyxX2";

        // get message///////////////////////////////
        Query referance = mFirebaseDatabase.getReference("messages").orderByChild("authors/"+userId).equalTo(true);
        referance.addChildEventListener(new ChildEventListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {

                    Message message = new Message(dataSnapshot.child("sender").getValue().toString(),
                            dataSnapshot.child("message").getValue().toString(),
                            Long.valueOf(dataSnapshot.child("date").getValue().toString()),dataSnapshot.child("chat").getValue().toString());
                    message.setid(dataSnapshot.getKey());
                    getmMessage().createIfNotExists(message);

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
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
        ///////////////////////////////////////////////////////////////
        ///////////////////////USERS////////////////////////////////////
        Query userReferance = mFirebaseDatabase.getReference("users");
        userReferance.addChildEventListener(new ChildEventListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {

                    User user = new User(dataSnapshot.getKey().toString(),dataSnapshot.child("displayName").getValue().toString());
                    getmUser().createIfNotExists(user);
                    System.out.println("Added User" + dataSnapshot.getValue());

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
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

    private void showNotification(String eventtext, Context ctx) {



        //We get a reference to the NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String MyText = "Reminder";
        Notification mNotification = new Notification(R.drawable.ic_launcher, MyText, System.currentTimeMillis() );
        //The three parameters are: 1. an icon, 2. a title, 3. time when the notification appears

        String MyNotificationTitle = "Medicine!";
        String MyNotificationText  = "Don't forget to take your medicine!";

        Intent MyIntent = new Intent(Intent.ACTION_VIEW);
        PendingIntent StartIntent = PendingIntent.getActivity(getApplicationContext(),0,MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        //A PendingIntent will be fired when the notification is clicked. The FLAG_CANCEL_CURRENT flag cancels the pendingintent

       // mNotification.Builder(getApplicationContext(), MyNotificationTitle, MyNotificationText, StartIntent);

        int NOTIFICATION_ID = 1;
        notificationManager.notify(NOTIFICATION_ID , mNotification);
    }

}
