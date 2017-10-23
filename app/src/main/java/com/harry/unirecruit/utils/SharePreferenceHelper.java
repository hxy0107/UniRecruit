package com.harry.unirecruit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.harry.unirecruit.constant.LibConstant;

import java.util.List;

/**
 * Created by 毕空 on 2017/10/23.
 */

public class SharePreferenceHelper {

    @Nullable
    private static SharePreferenceHelper sharePreferenceHelper = null;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;
    private Context context;

    private SharePreferenceHelper(@NonNull Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(LibConstant.SHAREPREFERENCE_NAME, Context.MODE_APPEND);
    }

    @Nullable
    public static SharePreferenceHelper getInstance(@NonNull Context context) {
        if (null == sharePreferenceHelper) {
            synchronized (SharePreferenceHelper.class) {
                sharePreferenceHelper = new SharePreferenceHelper(context);
            }
        }
        return sharePreferenceHelper;
    }

    /**
     * 获取已经初始化过的SharedPreferences对象
     * 可以直接使用
     *
     * @return SharedPreferences
     */
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * 获取已初始化的SharedPreferences.Editor对象
     *
     * @return SharedPreferences.Editor
     */
    public SharedPreferences.Editor getSharedPreferencesEditor() {
        return sharedPreferences.edit();
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, @Nullable String value) {
        if (value == null) {
            removeStorage(key);
        } else {
            SharedPreferences.Editor editor = getSharedPreferencesEditor();
            editor.putString(key, value);
            if (Build.VERSION.SDK_INT >= 9)
                editor.apply();
            else
                editor.commit();
        }
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor();
        editor.putInt(key, value);
        if (Build.VERSION.SDK_INT >= 9)
            editor.apply();
        else
            editor.commit();
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor();
        editor.putLong(key, value);
        if (Build.VERSION.SDK_INT >= 9)
            editor.apply();
        else
            editor.commit();
    }

    @SuppressLint("NewApi")
    public void saveStorage(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor();
        editor.putBoolean(key, value);
        if (Build.VERSION.SDK_INT >= 9)
            editor.apply();
        else
            editor.commit();
    }

    @SuppressLint("NewApi")
    public void removeStorage(String key) {
        SharedPreferences.Editor editor = getSharedPreferencesEditor();
        editor.remove(key);
        if (Build.VERSION.SDK_INT >= 9)
            editor.apply();
        else
            editor.commit();
    }

    public int getThemeVersion() {
        return sharedPreferences.getInt(LibConstant.THEME_VERSION, 0);
    }

    public void setThemeVersion(int version) {
        saveStorage(LibConstant.THEME_VERSION, version);
    }

    public void setThemeInfo(String version) {
        saveStorage(LibConstant.THEME_INfO, version);
    }


    @Nullable
    public <T> T getCachedObject(String key, Class<T> clazz){
        final String cachedStr = getSharedPreferences().getString(key, "");
        try {
            return JSON.parseObject(cachedStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public <T> List<T> getCachedObjectList(String key, Class<T> clazz){
        final String cachedStr = getSharedPreferences().getString(key, "");
        try {
            return JSON.parseArray(cachedStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public String getStringStorage(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

}
