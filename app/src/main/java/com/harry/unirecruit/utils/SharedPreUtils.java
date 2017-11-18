package com.harry.unirecruit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by 毕空 on 2017/10/23.
 */

public class SharedPreUtils {
    private static SharedPreUtils instance;

    private Context mContext;

    private SharedPreferences storage;

    private static final String USER_DATA = "userdata";

    private static final String TAG = "SharedPreUtils";


    public static final String VERSION_CODE = "version_code";


    public static final String ON = "on";

    public static final String OFF = "off";


    private SharedPreUtils(Context mContext) {
        this.mContext = mContext;
    }

    public  static SharedPreUtils getInstance(Context mContext) {
        if (instance == null) {
            synchronized (SharedPreUtils.class) {
                if(instance==null) {
                    instance = new SharedPreUtils(mContext);
                }
            }
        }
        return instance;
    }

    private void initStorage() {
        if (null != mContext && null == storage) {
            storage = mContext.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE);
        }
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, @Nullable String value) {
        initStorage();
        if (storage != null) {
            if (value == null) {
                removeStorage(key);
            } else {
                SharedPreferences.Editor editor = storage.edit();
                editor.putString(key, value);
                if (Build.VERSION.SDK_INT >= 9)
                    editor.apply();
                else
                    editor.commit();
            }
        }
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, int value) {
        initStorage();
        if (storage != null) {
            SharedPreferences.Editor editor = storage.edit();
            editor.putInt(key, value);
            if (Build.VERSION.SDK_INT >= 9)
                editor.apply();
            else
                editor.commit();
        }
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, long value) {
        initStorage();
        if (storage != null) {
            SharedPreferences.Editor editor = storage.edit();
            editor.putLong(key, value);
            if (Build.VERSION.SDK_INT >= 9)
                editor.apply();
            else
                editor.commit();
        }
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, boolean value) {
        initStorage();
        if (storage != null) {
            SharedPreferences.Editor editor = storage.edit();
            editor.putBoolean(key, value);
            if (Build.VERSION.SDK_INT >= 9)
                editor.apply();
            else
                editor.commit();
        }
    }

    public boolean getBooleanStorage(String key, boolean defaultValue) {
        initStorage();
        if (storage != null && !TextUtils.isEmpty(key)) {
            return storage.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    @Nullable
    public String getStorage(String key, String defaultValue) {
        initStorage();
        if (storage != null && !TextUtils.isEmpty(key)) {
            return storage.getString(key, defaultValue);
        }
        return defaultValue;
    }

    @SuppressLint("NewApi")
    public void removeStorage(String key) {
        initStorage();
        if (storage != null) {
            SharedPreferences.Editor editor = storage.edit();
            editor.remove(key);
            if (Build.VERSION.SDK_INT >= 9)
                editor.apply();
            else
                editor.commit();
        }
    }


    // setter getter
    @Nullable
    private String companyList;

    private long companyListCachedTime;

    private int versionCode;


    public int getVersionCode() {
        initStorage();
        if (storage != null) {
            versionCode = storage.getInt(VERSION_CODE, -1);
        }
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        Log.d(TAG, "versionCode=" + versionCode);
        this.versionCode = versionCode;
        saveStorage(VERSION_CODE, versionCode);
    }

    public static final String ENV_FLAG = "env_flag";
    @Nullable
    public String getEnvFlag() {
        initStorage();
        if (storage != null) {
            String envFlag = storage.getString(ENV_FLAG, null);
            if (TextUtils.isEmpty(envFlag)) {
                return null;
            }
            return envFlag;
        }
        return null;
    }

    public void setEnvFlag(String envFlag) {
        Log.d(TAG, "envFlag=" + envFlag);
        saveStorage(ENV_FLAG, envFlag);
    }

    // clear
    // 退出登录一定要调用
    public void clearStorage() {

    }

}
