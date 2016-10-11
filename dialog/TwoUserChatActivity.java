package com.otz.SCUchat.dialog;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.MessageDataSource;
import com.otz.SCUchat.bean.myMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class TwoUserChatActivity extends AppCompatActivity implements MessageDataSource.MessagesCallbacks{
    public static final String USER_EXTRA = "USER";

    public static final String TAG = "ChatActivity";

    private ArrayList<myMessage> mMessages;
    private MessagesAdapter mAdapter;
    private String mRecipient;
    private ListView mListView;
    private Date mLastMessageDate = new Date();
    private String mConvoId;
    private MessageDataSource.MessagesListener mListener;

    String newMessage;

    String senderName="";
    String receiverName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_user_chat);

        //mRecipient = "Ashok";

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        senderName = bundle.getString("currentUserName");
        receiverName = bundle.getString("clickedContactName");

        mListView = (ListView)findViewById(R.id.messages_list);
        mMessages = new ArrayList<>();
        mAdapter = new MessagesAdapter(mMessages);
        mListView.setAdapter(mAdapter);



        /*setTitle(mRecipient);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        String[] ids = {senderName,"-", receiverName};
        Arrays.sort(ids);
        mConvoId = ids[0]+ids[1]+ids[2];

        mListener = MessageDataSource.addMessagesListener(mConvoId, this);

        Button sendMessage = (Button)findViewById(R.id.send_message);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newMessageView = (EditText)findViewById(R.id.new_message);
                newMessage = newMessageView.getText().toString();
                newMessageView.setText("");
                myMessage msg = new myMessage();
                msg.setDate(new Date());
                msg.setText(newMessage);
                msg.setSender(senderName);

                MessageDataSource.saveMessage(msg, mConvoId);
            }
        });
    }

    @Override
    public void onMessageAdded(myMessage message) {
        mMessages.add(message);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageDataSource.stop(mListener);
    }


    private class MessagesAdapter extends ArrayAdapter<myMessage> {
        MessagesAdapter(ArrayList<myMessage> messages){
            super(TwoUserChatActivity.this, R.layout.message, R.id.message, messages);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            myMessage message = getItem(position);

            TextView nameView = (TextView)convertView.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)nameView.getLayoutParams();

            int sdk = Build.VERSION.SDK_INT;
            if (message.getSender().equals(senderName)){
                /*if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_right_green));
                } else{
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_right_green));
                }*/
                layoutParams.gravity = Gravity.RIGHT;
            }else{
                /*if (sdk >= Build.VERSION_CODES.JELLY_BEAN) {
                    nameView.setBackground(getDrawable(R.drawable.bubble_left_gray));
                } else{
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_left_gray));
                }*/
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);


            return convertView;
        }
    }
}