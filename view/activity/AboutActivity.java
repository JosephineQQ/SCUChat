
package com.otz.SCUchat.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.otz.SCUchat.markdown.MDReader;

import com.otz.SCUchat.R;

public class AboutActivity extends BaseActivity {
    
    private TextView mTextView;
    private MDReader mMDReader;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
	    getActionBar().setDisplayHomeAsUpEnabled(true);  
        getActionBar().setDisplayShowHomeEnabled(false); 
        super.onCreate(savedInstanceState);                     
	}

    @Override
    protected void initVariables() {
        mMDReader = new MDReader(getAboutAuthor());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_display);
        mTextView = (TextView)findViewById(R.id.DisplayTextView);        
    }

    @Override
    protected void loadData() {          
        mTextView.setTextKeepState(mMDReader.getFormattedContent(),BufferType.SPANNABLE);
    }
	
	protected String getVersionDescription() {        
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName + " for Android";
        } 
        catch (NameNotFoundException e) {            
            e.printStackTrace();
        }
        return "Unknow";
    }
	
	protected String getAboutAuthor() {
	    StringBuilder builder = new StringBuilder();
        builder.append("# **关于软件:**\n\n");
        builder.append("- 版本号: " + getVersionDescription() + "\n\n");
        builder.append("# **关于作者:**\n\n");
        builder.append("### 卢俊\n\n");
        builder.append("- 联系方式: lujun.hust@gmail.com \n\n");
        builder.append("- 个人网站: http://www.jhuster.com \n\n");        
        return builder.toString();
	}
}
