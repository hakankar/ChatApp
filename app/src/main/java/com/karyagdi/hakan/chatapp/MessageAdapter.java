package com.karyagdi.hakan.chatapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karyagdi.hakan.chatapp.orm_objects.Message;

import java.util.List;

/**
 * Created by hakan on 7/16/17.
 */

public class MessageAdapter extends ArrayAdapter {
    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<MessageTemplate> messages) {
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
        MessageTemplate model = (MessageTemplate) getItem(position);
        if (model != null) {

            TextView messageText, messageUser, messageTime;
            RelativeLayout message_layout;
            final float scale = getContext().getResources().getDisplayMetrics().density;
            int margin = (int) (5 * scale + 0.5f);
            messageText = (TextView) v.findViewById(R.id.message_text);
            messageUser = (TextView) v.findViewById(R.id.message_user);
            messageTime = (TextView) v.findViewById(R.id.message_time);
            message_layout = (RelativeLayout) v.findViewById(R.id.message_layout);
            Drawable drawable;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) message_layout.getLayoutParams();


            if (model.getsender().equals("Hakan Karyağdı"))
            {
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.my_message_shape);
                message_layout.setBackground(drawable);
                params.gravity=Gravity.RIGHT;
                params.rightMargin=margin;
                message_layout.setLayoutParams(params);

            }else
            {
                drawable = ContextCompat.getDrawable(getContext(),R.drawable.receive_message_shape);
                message_layout.setBackground(drawable);
                params.gravity=Gravity.LEFT;
                params.leftMargin=margin;
                message_layout.setLayoutParams(params);
            }


            messageText.setText(model.getmessage());
            messageUser.setText(model.getsender());
            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getdate()));

        }
        return v;

    }
}
