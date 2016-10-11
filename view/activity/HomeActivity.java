package com.otz.SCUchat.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.otz.SCUchat.R;
import com.otz.SCUchat.adapter.MyFragmentAdapter;
import com.otz.SCUchat.dialog.AddcontactsActivity;
import com.otz.SCUchat.dialog.GroupchatActivity;
import com.otz.SCUchat.dialog.ScanActivity;
import com.otz.SCUchat.dialog.WalletActivity;
import com.otz.SCUchat.view.fragment.ChatFragment;
import com.otz.SCUchat.view.fragment.ContactsFragment;
import com.otz.SCUchat.view.fragment.DiscoverFragment;
import com.otz.SCUchat.view.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements
        OnPageChangeListener, OnTabChangeListener {

    String login_user;
    //String login_id;

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = { ChatFragment.class, ContactsFragment.class,
            DiscoverFragment.class, MeFragment.class };
    private int imageViewArray[] = { R.mipmap.wexin_normal, R.mipmap.ic_contacts_normal,
            R.mipmap.ic_discover_normal, R.mipmap.ic_profile_normal };
    private String textViewArray[] = { "Notes", "Contacts", "Discover", "Me" };
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  setContentView(R.layout.main_tab_layout);*/
        setContentView(R.layout.activity_home);

        initView();
        initPage();


        /************显示在dialog里面传递过来的值******/
         login_user=getIntent().getStringExtra("username");
         //login_id=getIntent().getStringExtra("st_login_id");
         setTitle("WELCOME: "+login_user);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /* send login username from home activity to fragment*/
    public String getLoginUser(){
        return login_user;
    }


    /* 控件初始化 */
    private void initView() {
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setOnPageChangeListener(this);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.pager);
        mTabHost.setOnTabChangedListener(this);

        int count = textViewArray.length;

        for (int i = 0; i < count; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            mTabHost.setTag(i);
        }
    }

    /* 初始化Fragment */
    private void initPage() {
        /**********try to pass data from home activity to fragment**********/
        Bundle bundle=new Bundle();
        bundle.putString("pass_username", login_user);

        /************************************************************/
        ChatFragment fragment1 = new ChatFragment();
        ContactsFragment fragment2 = new ContactsFragment();
        DiscoverFragment fragment3 = new DiscoverFragment();
        MeFragment fragment4 = new MeFragment();

        fragment2.setArguments(bundle);    // Lifen: will this pass data or the above getLoginUser() function do????


        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), list));
    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.tab_content, null);
        ImageView mImageView = (ImageView) view
                .findViewById(R.id.tab_imageview);
        TextView mTextView = (TextView) view.findViewById(R.id.tab_textview);
        mImageView.setBackgroundResource(imageViewArray[i]);
        mTextView.setText(textViewArray[i]);
        return view;
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mTabHost.setCurrentTab(arg0);
        widget.setDescendantFocusability(oldFocusability);
        /* mTabHost.getTabWidget().getChildAt(arg0)
                .setBackgroundResource(R.drawable.selector_tab_background);*/
    }

    @Override
    public void onTabChanged(String tabId) {
        int position = mTabHost.getCurrentTab();
        vp.setCurrentItem(position);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        Intent intent;


        if (id == R.id.dialog_group_chat) {
            intent=new Intent(HomeActivity.this, GroupchatActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.dialog_add_contancts) {
            intent=new Intent(HomeActivity.this, AddcontactsActivity.class);
            intent.putExtra("User_name",login_user);
            startActivity(intent);
            return true;
        }
        if (id == R.id.dialog_scan_qr) {
            intent=new Intent(HomeActivity.this, ScanActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.dialog_wallet) {
            intent=new Intent(HomeActivity.this, WalletActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.dialog_userID) {
            /*TODO 显示主页用户*/
          setTitle("User: "+login_user);
            /*return true;*/
        }
        if (id == R.id.dialog_uninstall) {
            Uri packageURI=Uri.parse("package:com.otz.SCUchat");
            intent=new Intent(Intent.ACTION_DELETE,packageURI);
            startActivity(intent);
            /*return true;*/
        }

        return super.onOptionsItemSelected(item);
    }

}
