package com.otz.SCUchat.view.activity;

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

public class ZhuceActivity extends AppCompatActivity {

    EditText add_username,add_password,confirm_password;
    Button add_new_btn;

    String add_user;
    String add_pwd;
    String con_pwd;

    MyOpenHelper myDBHelper=new MyOpenHelper(this);
 /*  MyOpenHelper myDBHelper;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        add_username=(EditText)findViewById(R.id.add_username);
        add_password=(EditText)findViewById(R.id.add_pwd);
        confirm_password=(EditText)findViewById(R.id.confirm_new_pwd);


        add_new_btn=(Button)findViewById(R.id.add_btn);


        add_user = add_username.getText().toString().trim();
        add_pwd = add_password.getText().toString().trim();
        con_pwd = confirm_password.getText().toString().trim();

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



    public void signUp(View view){
       boolean isOk =this.doCheck();
        if(isOk==false){
            return;
        }
        boolean isDuplicateUsername=myDBHelper.checkUserName(add_user);
        if(isDuplicateUsername==true){
            Toast.makeText(getApplicationContext(), "username has existed please change one", Toast.LENGTH_SHORT).show();
            return;
        }

        //填写信息符合要求 可以注册
       boolean isInserted = myDBHelper.insertSignUp(add_user,add_pwd,null,null);
        if(isInserted==true){
            Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    public boolean doCheck() {
        add_user = add_username.getText().toString().trim();
        add_pwd = add_password.getText().toString().trim();
        con_pwd = confirm_password.getText().toString().trim();
        if (add_user.equals("")) {
            Toast.makeText(ZhuceActivity.this, "new username cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (add_pwd.equals("")) {
            Toast.makeText(ZhuceActivity.this, "new password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (con_pwd.equals("")) {
            Toast.makeText(ZhuceActivity.this, "please confirm your password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!add_pwd.equals("") && !con_pwd.equals("")) {
            if (!add_pwd.equals(con_pwd)) {
                Toast.makeText(ZhuceActivity.this, "password not the same", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }

}
