package com.otz.SCUchat.view.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.Iservice;
import com.otz.SCUchat.adapter.MusicService;



public class MusicShopActivity extends AppCompatActivity {

    public Iservice iservice;
    Myconn myconn;
    private static SeekBar seekBar;
    public static Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg){
            Bundle data=msg.getData();
            int duration = data.getInt("duration");
            int currentPosition=data.getInt("currentPosition");
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition);



        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_shop);

        Intent intent=new Intent(this, MusicService.class);
        startService(intent);

        myconn=new Myconn();
        bindService(intent,myconn,BIND_AUTO_CREATE);

        seekBar=(SeekBar)findViewById(R.id.seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iservice.callSeekTo(seekBar.getProgress());

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

    @Override
    protected void onDestroy(){
        unbindService(myconn);
        super.onDestroy();
    }

    public void clickMusic(View view){
      /*  MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource("/mnt/sdcard/Beat It.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        iservice.callplayMusic();
    }

    public void stopMusic(View view){
        iservice.callpauseMusic();

    }

    public void continuePlay(View view){
        iservice.callrePlay();

    }
    private class Myconn implements ServiceConnection{


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice=(Iservice)service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}



/*
package com.otz.SCUchat.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.otz.SCUchat.R;

public class MusicShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_shop);
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

}
*/
