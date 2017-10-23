package com.harry.unirecruit;

import android.app.Application;

/**
 * Created by 毕空 on 2017/10/23.
 */

public class UniApplication extends Application {
    private static UniApplication instance;
    public static UniApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;


    }
}
