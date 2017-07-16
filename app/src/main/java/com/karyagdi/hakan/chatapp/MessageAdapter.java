package com.karyagdi.hakan.chatapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.karyagdi.hakan.chatapp.orm_objects.Message;

import java.util.List;

/**
 * Created by hakan on 7/16/17.
 */

public class MessageAdapter extends ArrayAdapter {
    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<Message> messages) {
        super(context, resource, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.message_list, null);
        }
        Message model = (Message) getItem(position);
        if (model != null) {

            TextView messageText, messageUser, messageTime;
            messageText = (TextView) v.findViewById(R.id.message_text);
            messageUser = (TextView) v.findViewById(R.id.message_user);
            messageTime = (TextView) v.findViewById(R.id.message_time);

            messageText.setText(model.getMessageText());
            messageUser.setText(model.getuID());
            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

        }
        return v;

    }
}
