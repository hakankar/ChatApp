package com.karyagdi.hakan.chatapp.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
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
import com.karyagdi.hakan.chatapp.MainActivity;
import com.karyagdi.hakan.chatapp.MessageTemplate;
import com.karyagdi.hakan.chatapp.R;
import com.karyagdi.hakan.chatapp.Utility.BaseService;
import com.karyagdi.hakan.chatapp.Utility.Firebase;
import com.karyagdi.hakan.chatapp.Utility.MyProperties;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper;
import com.karyagdi.hakan.chatapp.orm_objects.Message;
import com.karyagdi.hakan.chatapp.orm_objects.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MessageService extends BaseService {
    public String userId;

    public MessageService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        importUsers();
        importMessages();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        userId = "AtUYqKbRS4NQ0SVClZvyAr2cyxX2";
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mFirebaseAuth = FirebaseAuth.getInstance();


        Log.v("username", Firebase.getAuthInstance().getCurrentUser().getDisplayName());

        super.onCreate();
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        Intent restartIntent = new Intent(getApplicationContext(),this.getClass());
//        restartIntent.setPackage(getPackageName());
//        startService(restartIntent);
        super.onTaskRemoved(rootIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void showNotification(String title, String text) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(text) // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
        @SuppressLint("WrongConstant") PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
        mBuilder.setContentIntent(pi);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }

    private void importUsers() {
        Query userReferance = Firebase.getDatebaseInstance().getReference("users");
        userReferance.addChildEventListener(new ChildEventListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {

                    User user = new User(dataSnapshot.getKey().toString(), dataSnapshot.child("displayName").getValue().toString());
                    getmUser().createIfNotExists(user);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User user = new User(dataSnapshot.getKey(), dataSnapshot.child("displayName").getValue().toString());
                try {
                    getmUser().update(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v("Removed ID: ", dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("HATA:  ", databaseError.getMessage());
            }
        });
    }

    private void importMessages() {
        Query referance = Firebase.getDatebaseInstance().getReference("messages").orderByChild("authors/" + userId).equalTo(true);
        final Vibrator vibra = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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
                            Long.valueOf(dataSnapshot.child("date").getValue().toString()), dataSnapshot.child("chat").getValue().toString());
                    message.setid(dataSnapshot.getKey());

                    System.out.println("userId " + message.getsender());
                    getmMessage().createIfNotExists(message);
                    if (!userId.equals(message.getsender()) ||
                            getmMessage().queryForEq("ID", dataSnapshot.getKey()).size() == 0 ||
                            !MyProperties.getInstance().currentChatId.equals(dataSnapshot.child("chat").getValue().toString())) {

                        List<User> sender = getmUser().queryForEq("USER_ID", message.getsender());
                        showNotification(sender.size() > 0 ? sender.get(0).getDisplayName() : null, message.getmessage());
                        vibra.vibrate(500);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v("Removed ID: ", dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("HATA:  ", databaseError.getMessage());
            }
        });

    }


}
