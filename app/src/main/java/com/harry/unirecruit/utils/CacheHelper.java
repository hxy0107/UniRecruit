package com.harry.unirecruit.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.harry.unirecruit.UniApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 毕空 on 2017/10/24.
 */

public abstract class CacheHelper<T> {
    protected final Class<T> typeParameterClass;
    protected List<T> mDataList = new ArrayList<>();
    protected File cache;

    public CacheHelper(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        this.cache = UniApplication.getInstance().getFileStreamPath(getCacheFileName());
        this.mDataList = getDataListFromCache();
    }

    public List<T> getDataListFromCache() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(cache);
            while (true) {
                byte[] targets = new byte[4];
                fis.read(targets);
                int target = (targets[0] & 0xff) | ((targets[1] << 8) & 0xff00)
                        | ((targets[2] << 24) >>> 8) | (targets[3] << 24);
                if (target == 0)
                    break;
                byte[] buffer = new byte[target];
                fis.read(buffer);
                mDataList = JSON.parseArray(new String(buffer), typeParameterClass);
            }
        } catch (Exception e) {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ignored) {
                }
            }
            cache.delete();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mDataList;
    }

    public void writeCache(List<T> list) {
        byte[] bytes = JSON.toJSONString(list).getBytes();
        cache.delete();
        if (bytes.length == 0) {
            return;
        }
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(cache, true);
            int len = bytes.length;
            byte[] targets = new byte[4];
            targets[0] = (byte) (len & 0xff);// 最低位
            targets[1] = (byte) ((len >> 8) & 0xff);// 次低位
            targets[2] = (byte) ((len >> 16) & 0xff);// 次高位
            targets[3] = (byte) (len >>> 24);// 最高位,无符号右移。
            fos.write(targets);
            fos.write(bytes);
            fos.close();
            fos = null;

        } catch (Exception e) {
            Log.v("error", e.toString());
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ignored) {
            }
            cache.delete();
        }
    }

    abstract String getCacheFileName();

    public abstract void update(T object);

    public abstract void add(T object);

    public abstract T getData(String key);
}
