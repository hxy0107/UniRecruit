package com.harry.unirecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harry.unirecruit.ui.BrowserActivity;
import com.harry.unirecruit.ui.HzUniListFragment;
import com.harry.unirecruit.ui.UniListFragment;

public class MainActivity extends AppCompatActivity {
    public static final int MODE_DEFAULT = 0;

    public static final int MODE_SONIC = 1;

    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 2;

    public static final int MODE_LOAD_CACHE_ELSE_NETWORK = 3;

    private static final int PERMISSION_REQUEST_CODE_STORAGE = 1;

    private static final String DEMO_URL = "http://mc.vip.qq.com/demo/indexv3";

//    private TextView mTextMessage;
//    private Button mButton;

    private FragmentManager manager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    manager.beginTransaction().replace(R.id.fragment_container, new UniListFragment()).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    manager.beginTransaction().replace(R.id.fragment_container, new HzUniListFragment()).commitAllowingStateLoss();
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mTextMessage = (TextView) findViewById(R.id.message);
//        mButton=(Button)findViewById(R.id.button);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startBrowserActivity(MODE_LOAD_CACHE_ELSE_NETWORK);
//
//            }
//        });

        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.fragment_container, new UniListFragment()).commitAllowingStateLoss();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void startBrowserActivity(int mode) {
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra(BrowserActivity.PARAM_URL, "http://hr.whu.edu.cn/");
        intent.putExtra(BrowserActivity.PARAM_MODE, mode);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivityForResult(intent, -1);
    }
}
