package com.otz.SCUchat.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.MyOpenHelper;
import com.otz.SCUchat.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

public class GroupchatActivity extends AppCompatActivity {
    private Activity ctx;
    private View layout;

    private List<MsgBean> msgList=new ArrayList<MsgBean>();
    private ListView chat_list_view;
    private EditText input_text;
    private Button send_button;
    private MyChatAdapter chatAdapter;

    String p_username="";
    String friend_name = "";

    MyOpenHelper myOpenHelper=new MyOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupchat);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        p_username = bundle.getString("currentUserName");
        friend_name = bundle.getString("clickedContactName");   // Lifen: haven't use db yet, just pass parameter //////////////////


        initData();

        chat_list_view = (ListView) findViewById(R.id.list_view_chat);
        input_text = (EditText) findViewById(R.id.input_text_chat);
        send_button = (Button) findViewById(R.id.send_chat);

        chatAdapter = new MyChatAdapter(this, R.layout.chat_item_row, msgList);
        chat_list_view.setAdapter(chatAdapter);

        send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {       // click listener to "send" button
                String text = input_text.getText().toString();
                if (!text.equals("")) {        // if message is not empty
                    MsgBean msg = new MsgBean(text, 1);    // send is 1, receiver is 0
                    msgList.add(msg);         // add message to list
                    myOpenHelper.insertMsg(p_username, friend_name, text);
                    // add the db
                    chatAdapter.notifyDataSetChanged();         // notify adapter to update
                    chat_list_view.setSelection(msgList.size());
                    input_text.setText("");
                }
            }
        });

    }



    private void initData() {     // test, pre-load chat message
        /*msgList.add(new Msg("Hello.", Msg.TYPE_R));
        msgList.add(new Msg("Hello. Who is this?", Msg.TYPE_S));
        msgList.add(new Msg("This is Lily. How are you? ", Msg.TYPE_R));*/


        msgList=myOpenHelper.getMsgBetweenTwoUser(p_username, friend_name);
    }

    private class MyChatAdapter extends ArrayAdapter<MsgBean> {
        private int resouceId;

        public MyChatAdapter(Context context, int resource, List<MsgBean> objects) {
            super(context, resource, objects);
            resouceId=resource;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MsgBean msg = getItem(position);
            ViewHolder holder;
            View view;

            if(convertView==null){
                view=View.inflate(getContext(), resouceId, null);
                holder=new ViewHolder();
                holder.left_layout = (LinearLayout) view.findViewById(R.id.left_layout);
                holder.right_layout = (LinearLayout) view.findViewById(R.id.right_layout);
                holder.left_msg=(TextView) view.findViewById(R.id.left_msg);
                holder.right_msg=(TextView) view.findViewById(R.id.right_msg);
                view.setTag(holder);
            }else{
                view=convertView;
                holder = (ViewHolder) view.getTag();
            }

            if(msg.msgType== 0){
                holder.left_layout.setVisibility(View.VISIBLE);
                holder.right_layout.setVisibility(View.GONE);
                holder.left_msg.setText(msg.msgText);
            }else if(msg.msgType== 1){
                holder.left_layout.setVisibility(View.GONE);
                holder.right_layout.setVisibility(View.VISIBLE);
                holder.right_msg.setText(msg.msgText);
            }

            return view;
        }

        class ViewHolder{
            LinearLayout left_layout,right_layout;
            TextView left_msg,right_msg;
        }
    }


}
