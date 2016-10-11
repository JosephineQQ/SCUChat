
package com.otz.SCUchat.adapter;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.otz.SCUchat.view.activity.MusicShopActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by OTZ on 3/17/2016.
 */
public class MusicService extends Service{
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate(){
        mediaPlayer=new MediaPlayer();
        super.onCreate();
    }

    @Override
    public void onDestroy(){

    }

    public void seekTo(int pos){
        mediaPlayer.seekTo(pos);
    }

    public void playMusic(){

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource("/mnt/sdcard/japan2.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();

            updateSeekBar();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("play ~~~~~~~~~~~~~~~~~~~~~~~~~`");
    }

    public void updateSeekBar(){
        final int duration=mediaPlayer.getDuration();
        Timer timer =new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {

                int currentPosition= mediaPlayer.getCurrentPosition();
                Message msg= Message.obtain();

                Bundle bundle=new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentPosition",currentPosition);

                msg.setData(bundle);
                MusicShopActivity.handler.sendMessage(msg);



            }
        };
        timer.schedule(task,100,1000);

        // music play end, cancel timer timertask

    }

    public void pauseMusic(){
        System.out.println("pausepausepausepausepausepausepausepause ~~~~~~~~~~~~~~~~~~~~~~~~~`");
        mediaPlayer.pause();

    }

    public void rePlay(){
        System.out.println("rererererererere ~~~~~~~~~~~~~~~~~~~~~~~~~`");
        mediaPlayer.start();

    }

    private class MyBinder extends Binder implements Iservice{

        @Override
        public void callSeekTo(int pos){
            seekTo(pos);
        }

        @Override
        public void callplayMusic() {
            playMusic();
        }

        @Override
        public void callpauseMusic() {
            pauseMusic();
        }

        @Override
        public void callrePlay() {
            rePlay();
        }
    }
}

/*
package com.otz.SCUchat.adapter;

*/
/**
 * Created by OTZ on 3/18/2016.
 *//*

public class MusicService {
}
*/
