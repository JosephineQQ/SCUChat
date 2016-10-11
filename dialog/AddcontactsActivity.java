package com.otz.SCUchat.dialog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.MyOpenHelper;

public class AddcontactsActivity extends AppCompatActivity {
    MyOpenHelper myOpenHelper=new MyOpenHelper(this);

    Button add_newContact_btn, view_newContact_btn;
    EditText newContact_username;

    String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontacts);

        newContact_username=(EditText)findViewById(R.id.add_newContacts_et);

        add_newContact_btn=(Button)findViewById(R.id.add_newContact_btn);
        view_newContact_btn=(Button)findViewById(R.id.view_newCOntacts_btn);

        UserName = getIntent().getStringExtra("User_name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    //添加之前需要查询数据库是否存在此username    // Lifen: added
    public void addContact(View view){
        boolean isInserted=myOpenHelper.insertFriend(UserName, newContact_username.getText().toString().trim());
        if(isInserted==true){
            Toast.makeText(getApplicationContext(), "Friend added", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"This user doesn't exist, or is your friend already",Toast.LENGTH_SHORT).show();
        }

        //finish();
    }

    public void viewContact(View view){
        boolean isFriendExist=myOpenHelper.checkFriendExist(newContact_username.getText().toString().trim());
        if(isFriendExist == true){
            Toast.makeText(getApplicationContext(), "Yes, contact exist", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"No, contact doesn't exist",Toast.LENGTH_SHORT).show();
        }
    }

}
