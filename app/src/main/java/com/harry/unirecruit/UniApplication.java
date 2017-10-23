package com.harry.unirecruit;

import android.app.Application;

import com.harry.unirecruit.dao.UniData;
import com.harry.unirecruit.utils.SharedPreUtils;

import java.util.List;

/**
 * Created by 毕空 on 2017/10/23.
 */

public class UniApplication extends Application {
    private static UniApplication appContext;
    private static SharedPreUtils mSharedPreUtils;
    private static List<UniData> mUniData;

    public synchronized static UniApplication getInstance() {
        if (appContext == null) {
            appContext = new UniApplication();
        }
        if (mSharedPreUtils == null) {
            mSharedPreUtils = SharedPreUtils.getInstance(appContext);
        }
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        mSharedPreUtils = SharedPreUtils.getInstance(appContext);
    }



}
