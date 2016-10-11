package com.otz.SCUchat.view.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otz.SCUchat.R;
import com.otz.SCUchat.view.activity.GameActivity;
import com.otz.SCUchat.view.activity.MomentActivity;
import com.otz.SCUchat.view.activity.MusicShopActivity;


public class DiscoverFragment extends Fragment implements View.OnClickListener {
    private Activity ctx;
    private View layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_discover, null);
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
      //  View view=inflater.inflate(R.layout.fragment_discover, null);

       // ListView lv_discover=(ListView)view.findViewById(R.id.lv_discover_fragment);

      //  return view;
    }

    private void initViews() {

    }
    public void initData(){

    }
    public void setOnListener(){
        layout.findViewById(R.id.txt_pengyouquan).setOnClickListener(this);
        layout.findViewById(R.id.txt_game).setOnClickListener(this);
        layout.findViewById(R.id.txt_shop).setOnClickListener(this);
        layout.findViewById(R.id.txt_nearby).setOnClickListener(this);
       /* layout.findViewById(R.id.txt_saoyisao).setOnClickListener(this);
        layout.findViewById(R.id.txt_yaoyiyao).setOnClickListener(this);
        layout.findViewById(R.id.txt_nearby).setOnClickListener(this);
        layout.findViewById(R.id.txt_piaoliuping).setOnClickListener(this);
        layout.findViewById(R.id.txt_shop).setOnClickListener(this);
        layout.findViewById(R.id.txt_game).setOnClickListener(this);*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_pengyouquan:
                Intent intent=new Intent(getActivity(), MomentActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_game:
                Intent intent2=new Intent(getActivity(), GameActivity.class);
                startActivity(intent2);
                break;
            case R.id.txt_shop:
                Intent intent3=new Intent(getActivity(), MusicShopActivity.class);
                startActivity(intent3);
                break;
            case R.id.txt_nearby:
              /*  Intent intent4=new Intent(getActivity(), NearbyActivity.class);
                startActivity(intent4);*/
                Intent intentNote = new Intent(Intent.ACTION_MAIN);
                intentNote.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity"));
                startActivity(intentNote);
                break;

            /*  public void launchNote(View view){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.jhuster.jnote", "com.jhuster.jnote.MainActivity")); //包裹名稱，要開啟的頁面
        //intent.putExtra("value", "test"); //要傳送的值
        startActivity(intent);
    }*/
          //很多没有实现
            default:
                break;
        }

    }

/*    private class MyDiscoverAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView==null){
                View.inflate(DiscoverFragment.this,R.layout.item_discover,null);

            }
            return null;
        }
    }*/
}
