package com.otz.SCUchat.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.MyOpenHelper;
import com.otz.SCUchat.bean.UserBean;
import com.otz.SCUchat.dialog.ContactInfoActivity;
import com.otz.SCUchat.view.activity.HomeActivity;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private Activity ctx;
    private View layout;
    TextView show_pusername;
    ArrayList<UserBean> findLists;
    MyOpenHelper myOpenHelper;
    MyContactAdapter contactAdapter=new MyContactAdapter();

    String p_username;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*View view = inflater.inflate(R.layout.fragment_contacts, null);
        return view;*/

        /*得到home传递过来的login id*/
        HomeActivity homeActivity=(HomeActivity)getActivity();
         p_username=homeActivity.getLoginUser();

        myOpenHelper=new MyOpenHelper(getActivity());
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_contacts, null);

            /*验证从activity传递过来的login username可以获取 显示到textview*/
            show_pusername=(TextView)layout.findViewById(R.id.show_pid);
            show_pusername.setText(p_username.trim());

            findLists=myOpenHelper.findFriendID(p_username);


            ListView lv=(ListView)layout.findViewById(R.id.contacts_lv);
            lv.setAdapter(contactAdapter);

            // Lifen added, click a friend, go to friend's infomation page as "ContactInfoActivity"
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                    UserBean clickedContactInfo = findLists.get(position);
                    final Intent intent = new Intent(getActivity(), ContactInfoActivity.class);
                    intent.putExtra("currentUserName", p_username);
                    //intent.putExtra("clickedContactUID", clickedContactInfo.getUID());
                    intent.putExtra("clickedContactName", clickedContactInfo.getUsername());
                    intent.putExtra("clickedContactIconPath", clickedContactInfo.getIconPath());
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

    public void onResume(){
        super.onResume();
        findLists.clear();
        findLists=myOpenHelper.findFriendID(p_username);
        contactAdapter.notifyDataSetChanged();
    }



    private class MyContactAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return findLists.size();
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

                // 第一种获得打气筒
                view = View.inflate(ctx.getApplicationContext(),R.layout.contact_item_row,null);
                // 第2种获得打气筒
                //view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item_row,null);
                //第3种获得打气筒
               /* LayoutInflater inflater=(LayoutInflater)layout.getSystemService(LAYOUT_INFLATER_SERVICE);*/
               // view=inflater.inflate(R.layout.list_item_row,null);

            }else{
                view=convertView;
            }

            /*找到控件，显示数据*/
            TextView tv_friend_name=(TextView)view.findViewById(R.id.tv_contact_friendname);
            UserBean userBean=findLists.get(position);
            /*差显示图片*/
            /*TODO*/
            tv_friend_name.setText(userBean.getUsername());

            return view;

        }
    }

}
