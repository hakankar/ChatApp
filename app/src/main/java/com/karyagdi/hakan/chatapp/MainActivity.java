package com.karyagdi.hakan.chatapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karyagdi.hakan.chatapp.orm_objects.Chat;
import com.karyagdi.hakan.chatapp.orm_objects.Message;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<MessageTemplate> adapter;
    RelativeLayout activity_main;

    FloatingActionButton btnSendMessage;
    Chat chat = new Chat();
    Message message = new Message();
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Snackbar.make(activity_main,"You have been signed out.", Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Snackbar.make(activity_main,"Successfully signed in.Welcome!", Snackbar.LENGTH_SHORT).show();
                // if(isNetworkAvailable()) {
                displayChatMessage();
                //}
                //else
                //{
                //   displayOfflineChatMessage();
                //}

            }
            else{
                Snackbar.make(activity_main,"We couldn't sign you in.Please try again later", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity_main = (RelativeLayout)findViewById(R.id.activity_main);


        FirebaseDatabase.getInstance().getReference().child("-Kor08MXb5BIIyMlqEmL").child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               if(!chat.containsMessage(dataSnapshot.getKey()))
               {
                Message newMessage = dataSnapshot.getValue(Message.class);
                  message= new Message(newMessage.getsender(),newMessage.getmessage(),newMessage.getdate());
                  message.setchat("-Kor08MXb5BIIyMlqEmL");
                  message.setid(dataSnapshot.getKey());
                  message.save();
                  Log.v("Mesaj alındı",String.valueOf(Message.listAll(Message.class).size()));
               }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btnSendMessage = (FloatingActionButton)findViewById(R.id.btnSendMessage);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
                if (chat==null)
                {
                    DatabaseReference refChat =FirebaseDatabase.getInstance().getReference().push();
                    chat=new Chat(refChat.getKey());
                    chat.save();
                }
                MessageTemplate newMessage =new MessageTemplate(txtMessage.getText().toString(),
                                                       FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                                       FirebaseAuth.getInstance().getCurrentUser().getUid(),new Date().getTime());

                //chat.addMessages(chatMessage);
                //HashMap<String,Chat> chatMap =new HashMap<>();
                //chatMap.put("-Kor08MXb5BIIyMlqEmL",chat);


                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("-Kor08MXb5BIIyMlqEmL").child("messages").push();
                ref.setValue((newMessage));
                message =new Message(newMessage.getuID(),newMessage.getMessageText(),newMessage.getMessageTime());
                message.setchat(chat.getid());
                message.setid(ref.getKey());
                message.save();
                txtMessage.getText().clear();
            }
        });


        if(FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else
        {
            //Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),Snackbar.LENGTH_SHORT).show();
           // if(isNetworkAvailable()) {
                displayChatMessage();
            //}
            //else
            //{
             //   displayOfflineChatMessage();
            //}
        }


    }



    private void displayChatMessage() {

        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<MessageTemplate>(this,MessageTemplate.class,R.layout.message_list,FirebaseDatabase.getInstance().getReference().child("-Kor08MXb5BIIyMlqEmL").child("messages"))
        {
            @Override
            protected void populateView(View v, MessageTemplate model, int position) {

                TextView messageText, messageUser, messageTime;
                messageText = (TextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getuID());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
            }
        };
        listOfMessage.setAdapter(adapter);
    }

    private void displayOfflineChatMessage() {

        MessageAdapter messageAdapter = new MessageAdapter(this,R.layout.message_list,chat.getMessages());

        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);

        listOfMessage.setAdapter(messageAdapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}