package com.otz.SCUchat.view.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.view.activity.HomeActivity;


public class ChatFragment extends Fragment {

    private Activity ctx;
    private View layout;
    Button btn;
    TextView textView;
    //MyOpenHelper myOpenHelper;    // database

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        HomeActivity homeActivity=(HomeActivity)getActivity();

       // String p_uid=homeActivity.getLoginID();           //get the login_id from home activity/

        //myOpenHelper=new MyOpenHelper(getActivity());          // database
       if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_chat, null);
            //findLists=myOpenHelper.findFriendID(p_uid);      *//*check loginid in database

           initViews();
           initData();
           setOnListener();

           btn=(Button)layout.findViewById(R.id.noteGoto);
           btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Intent.ACTION_MAIN);
                   intent.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity")); //包裹名稱，要開啟的頁面
                   //intent.putExtra("value", "test"); //要傳送的值
                   startActivity(intent);
               }
           });

           textView=(TextView)layout.findViewById(R.id.tv_note);
           textView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Intent.ACTION_MAIN);
                   intent.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity")); //包裹名稱，要開啟的頁面
                   //intent.putExtra("value", "test"); //要傳送的值
                   startActivity(intent);
               }
           });


        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if(parent!=null){
                parent.removeView(layout);
            }

        }


        return layout;
    }

    private void initViews() {

    }
    public void initData(){

    }

    public void setOnListener(){

       /* layout.findViewById(R.id.txt_saoyisao).setOnClickListener(this);
        layout.findViewById(R.id.txt_yaoyiyao).setOnClickListener(this);
        layout.findViewById(R.id.txt_nearby).setOnClickListener(this);
        layout.findViewById(R.id.txt_piaoliuping).setOnClickListener(this);
        layout.findViewById(R.id.txt_shop).setOnClickListener(this);
        layout.findViewById(R.id.txt_game).setOnClickListener(this);*/

    }
  /*  public void clickNote(View view){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity")); //包裹名稱，要開啟的頁面
        //intent.putExtra("value", "test"); //要傳送的值
        startActivity(intent);*/
    //}


    public void clickNote() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity")); //包裹名稱，要開啟的頁面
        //intent.putExtra("value", "test"); //要傳送的值
        startActivity(intent);
    }
}
