package com.otz.SCUchat.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.otz.SCUchat.R;

// Lifen added, friend's info page, have button to start chat, go to "GroupchatActivity"
public class ContactInfoActivity extends AppCompatActivity {

    String friendUserNmae;
    String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TextView friendName = (TextView) findViewById(R.id.friendName_display);
        TextView friendInfo = (TextView) findViewById(R.id.friendInfo_display);
        ImageView imageView = (ImageView) findViewById(R.id.friendImageView_display);
        Button buttonStartChat = (Button) findViewById(R.id.buttonSendMsg);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        currentUserName = bundle.getString("currentUserName");
        friendUserNmae = bundle.getString("clickedContactName");
        friendName.setText(bundle.getString("clickedContactName"));
        //friendInfo.setText(bundle.getString("currentdescript"));
        imageView.setImageResource(bundle.getInt("clickedContactIconPath"));

        buttonStartChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(ContactInfoActivity.this, TwoUserChatActivity.class);
                intent.putExtra("currentUserName", currentUserName);
                intent.putExtra("clickedContactName", friendUserNmae);
                startActivity(intent);
            }
        });
    }

}
