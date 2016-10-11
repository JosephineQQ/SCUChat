
package com.otz.SCUchat.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.otz.SCUchat.R;

public class GameActivity extends AppCompatActivity {

    Bitmap alterBitmap;
    int b=-7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final ImageView imageView=(ImageView)findViewById(R.id.iv_monalisa);

        Bitmap srcBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.mona);
        alterBitmap=Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());

        Paint paint=new Paint();
        Canvas canvas=new Canvas(alterBitmap);

        canvas.drawBitmap(srcBitmap,new Matrix(),paint);
        imageView.setImageBitmap(alterBitmap);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {


                    case MotionEvent.ACTION_MOVE:

                        for (int i = -30; i < 30; i++) {
                            for (int j = -30; j < 30; j++) {

                                alterBitmap.setPixel((int) event.getX()+i, (int) event.getY()+j, Color.TRANSPARENT);
                            }
                        }

                        imageView.setImageBitmap(alterBitmap);
                        break;
                }

                return true;
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

}

/*
package com.otz.SCUchat.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.otz.SCUchat.R;

public class GameActivity extends AppCompatActivity {

    Bitmap alterBitmap;
    int b=-7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);




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
