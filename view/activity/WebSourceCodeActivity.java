package com.otz.SCUchat.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.util.StreamTools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebSourceCodeActivity extends AppCompatActivity {
    EditText et_path;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_source_code);

        et_path=(EditText)findViewById(R.id.et_path);
        tv_result=(TextView)findViewById(R.id.tv_result);


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

    public void click(View view){

        String path=et_path.getText().toString().trim();
        if(path==null||path.equals("")){
            tv_result.setText("Path can not be empty");
           // return;
        }

            //用到httpurlconnection
        try {
            URL url=new URL(path);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int code=conn.getResponseCode();
            if(code==200){
                InputStream in=conn.getInputStream();
                String content= StreamTools.readStream(in);
                System.out.println(content);
                tv_result.setText(content);

            }else{
                tv_result.setText("no nono");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
