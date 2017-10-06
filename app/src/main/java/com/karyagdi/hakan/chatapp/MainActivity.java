package com.karyagdi.hakan.chatapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.j256.ormlite.dao.RawRowMapper;
import com.karyagdi.hakan.chatapp.Utility.BaseActivity;
import com.karyagdi.hakan.chatapp.Utility.Firebase;
import com.karyagdi.hakan.chatapp.Utility.MyProperties;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.ChatUser;
import com.karyagdi.hakan.chatapp.orm_objects.DatabaseHelper;
import com.karyagdi.hakan.chatapp.orm_objects.Message;
import com.karyagdi.hakan.chatapp.services.MessageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<MessageTemplate> adapter;
    RelativeLayout activity_main;

    FloatingActionButton btnSendMessage;
    DatabaseHelper DatabaseHelper;
    String chatId;
    Intent messageService;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main, "You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                stopService(messageService);
                startService(messageService);

                displayOfflineChatMessage();

            } else {
                Snackbar.make(activity_main, "We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        messageService = new Intent(this, MessageService.class);
        DatabaseHelper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_main);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);
        chatId = "-Kor08MXb5BIIyMlqEmL";
        String user1 = "AtUYqKbRS4NQ0SVClZvyAr2cyxX2";
        String user2 = "vjvT7az7egfhBNcQkA2MRXJA4xf2";
        try {

            getmChat().createIfNotExists(new Chat(chatId));
            getmChatUser().create(new ChatUser(chatId, user1));
            getmChatUser().create(new ChatUser(chatId, user2));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        btnSendMessage = (FloatingActionButton) findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtMessage = (EditText) findViewById(R.id.txtMessage);

                try {


                    List<String> list = getmChatUser().queryRaw("select USER_ID from ChatUser where CHAT_ID='" + chatId + "'", new RawRowMapper<String>() {
                        @Override
                        public String mapRow(String[] columnNames, String[] resultColumns) throws SQLException {
                            return resultColumns[0];
                        }
                    }).getResults();

                    ArrayList<String> authors = new ArrayList<String>(list);
                    Message newMessage = new Message(Firebase.getAuthInstance().getCurrentUser().getUid(), txtMessage.getText().toString(), new Date().getTime(), chatId);

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("messages").push();
                    ref.setValue((newMessage));
                    for (String user : authors) {
                        ref.child("authors").child(user).setValue(true);
                    }

                    System.out.println("authors " + authors);
                    //Message message =new Message(newMessage.getsender(),newMessage.getmessage(),newMessage.getdate());
                    newMessage.setid(ref.getKey());
                    getmMessage().create(newMessage);
                    displayOfflineChatMessage();
                    txtMessage.getText().clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


        if (Firebase.getAuthInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQUEST_CODE);
            MyProperties.getInstance().currentChatId = "-Kor08MXb5BIIyMlqEmL";

        } else {
            //Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Snackbar.LENGTH_SHORT).show();
            // if(isNetworkAvailable()) {
            // displayChatMessage();
            displayOfflineChatMessage();
            //}
            //else
            //{
            //   displayOfflineChatMessage();
            //}
        }


    }


    private void displayChatMessage() {

        ListView listOfMessage = (ListView) findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<MessageTemplate>(this, MessageTemplate.class, R.layout.message_list, FirebaseDatabase.getInstance().getReference("chats").child(chatId).child("messages")) {
            @Override
            protected void populateView(View v, MessageTemplate model, int position) {

                TextView messageText, messageUser, messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getmessage());
                messageUser.setText(model.getsender());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getdate()));
            }
        };
        listOfMessage.setAdapter(adapter);
    }

    private void displayOfflineChatMessage() {
        // List<Message> messages=new ArrayList<Message>();
        List<MessageTemplate> messages = new ArrayList<MessageTemplate>();

//        try {
//            messages = getmMessage().query(
//                    getmMessage().queryBuilder().where()
//                            .eq("CHAT_ID",chatId)
//                            .prepare());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        Cursor cursor = getDatabaseHelper().getReadableDatabase().rawQuery(
                "SELECT M.MESSAGE, M.DATE, U.DISPLAY_NAME " +
                        "FROM MESSAGE M " +
                        "INNER JOIN USER U ON M.SENDER_ID = U.USER_ID " +
                        "WHERE M.CHAT_ID ='" + chatId + "'", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            messages.add(new MessageTemplate(
                    cursor.getString(cursor.getColumnIndex("MESSAGE")),
                    cursor.getString(cursor.getColumnIndex("DISPLAY_NAME")),
                    Long.valueOf(cursor.getString(cursor.getColumnIndex("DATE")))
            ));

        }


        MessageAdapter messageAdapter = new MessageAdapter(this, R.layout.message_list, messages);

        ListView listOfMessage = (ListView) findViewById(R.id.list_of_message);

        listOfMessage.setAdapter(messageAdapter);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showNotification() {


    }

}