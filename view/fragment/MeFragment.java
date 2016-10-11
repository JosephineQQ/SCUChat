package com.otz.SCUchat.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.util.Constants;
import com.otz.SCUchat.util.Utils;
import com.otz.SCUchat.view.activity.MomentActivity;
import com.otz.SCUchat.view.activity.WebSourceCodeActivity;


public class MeFragment extends Fragment implements View.OnClickListener {

    private Activity ctx;
    private View layout;
    private TextView tvname,tv_account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       /* View view=inflater.inflate(R.layout.fragment_me, null);
        return view;*/
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_me,null);
            initViews();
            initData();
            setOnListener();
        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if(parent!=null){
                parent.removeView(layout);
            }
        }


        return layout;
    }

    public void initViews(){
        tvname=(TextView)layout.findViewById(R.id.tvname);
        tv_account=(TextView)layout.findViewById(R.id.tvmsg);

        String id= Utils.getValue(getActivity(), Constants.User_ID);
        tv_account.setText("WeChat ID"+" : "+id);
       /* if(GloableParams.UserInfos!=null){
           // String name=UserUtils.getUserName(ctx);
            *//*************************未完成******************************//*
            *//*************************未完成******************************//*
            *//*************************未完成******************************//*
            *//*************************未完成******************************//*
            *//*************************未完成******************************//*
        }*/


    }
    public void initData(){

    }
    public void setOnListener(){

        layout.findViewById(R.id.view_user).setOnClickListener(this);
        layout.findViewById(R.id.txt_smail).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()){
            case R.id.view_user:
              intent=new Intent(getActivity(), MomentActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_smail:
                intent=new Intent(getActivity(),WebSourceCodeActivity.class);
                startActivity(intent);

        }
    }



}
