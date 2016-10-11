package com.otz.SCUchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.otz.SCUchat.adapter.MyOpenHelper;
import com.otz.SCUchat.bean.UserInfo;
import com.otz.SCUchat.view.activity.HomeActivity;
import com.otz.SCUchat.view.activity.ZhuceActivity;

import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    MyOpenHelper myOpenHelper;

    EditText username, password;
    CheckBox isRemember;
    Button goToSign_btn;

    String name;
    String pwd;

    int login_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /************test数据库用******************************/
         myOpenHelper=new MyOpenHelper(getApplicationContext());
      // SQLiteDatabase sqLiteDatabase = myOpenHelper.getWritableDatabase();
        /************************************************/


        username=(EditText)findViewById(R.id.username_edit_text);
        password=(EditText)findViewById(R.id.userpassword_edit_text);
        isRemember=(CheckBox)findViewById(R.id.isCheck_remember_cb);
       /* showNewUser();*/

        //read info.txt to LoginActivity if isRemember checked
        Map<String,String> maps = UserInfo.readInfo(LoginActivity.this);
        if(maps!=null){
            //checkBos checked
           /* isRemember.setChecked(!isRemember.isChecked());*/
            // get name and pwd out
            String name=maps.get("name");
            String pwd=maps.get("pwd");
            //show in editText
            username.setText(name);
            password.setText(pwd);
        }

        goToSign_btn=(Button)findViewById(R.id.signup_btn);
        goToSign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginActivity.this, "AAAAAAAAAAAA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, ZhuceActivity.class);
                startActivity(intent);
            }
        });

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

    public boolean doPreCheck(){
        name=username.getText().toString().trim();
        pwd=password.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            Toast.makeText(LoginActivity.this,"username and password can not be empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    /*************************************************************************************/
    /*暂时没有实现isRemember方法*/


    //get username password
    public void login(View view){   // after click "login"
        boolean isOk=doPreCheck();
        if(isOk==false){ return; }

        Boolean user_info_check = myOpenHelper.checkToLogin(name,pwd);
        if(user_info_check == false){
            Toast.makeText(LoginActivity.this,"username or password not correct!",Toast.LENGTH_SHORT).show();
            return;
        }else{
            String st_login_id=String.valueOf(login_id);
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra("username", name);
            //intent.putExtra("st_login_id",st_login_id);
            startActivity(intent);
        }



       /*  name=username.getText().toString().trim();
         pwd=password.getText().toString().trim();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            Toast.makeText(LoginActivity.this,"username and password can not be empty",Toast.LENGTH_SHORT).show();
        }else{
            System.out.println("connect to server database~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            if(isRemember.isChecked()){
                //store username and password if checked
                boolean result= UserInfo.saveInfo(LoginActivity.this,name,pwd);
                if(result){
                    Toast.makeText(LoginActivity.this,"save in remember sucessfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"save failed!",Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(LoginActivity.this,"checkBox false, not remember usermane password",Toast.LENGTH_SHORT).show();
            }
        }*/

    }


    //注册新用户后返回登录页面 显示新注册用户名 密码 打回
    /*public void showNewUser(){
        Intent intent=getIntent();
        String newUserBack=intent.getStringExtra("username");
        String newPwdBack=intent.getStringExtra("password");
        username.setText(newUserBack);
        password.setText(newPwdBack);

    }*/

}
