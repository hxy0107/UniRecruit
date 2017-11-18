package com.harry.unirecruit.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.harry.unirecruit.MainActivity;
import com.harry.unirecruit.R;
import com.harry.unirecruit.SonicJavaScriptInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 毕空 on 2017/11/18.
 */

public class HzUniListFragment extends ListFragment {

    private ClipboardManager mClipboardManager;

    private String[] values = new String[]{"浙江大学", "浙江工业大学", "杭州电子科技大学", "浙江工商大学", "浙江理工大学", "杭州师范大学", "中国计量学院", "[无效]中国美术学院",

            "浙江中医药大学(二本)","浙江科技学院(二本)","[无效]浙江林学院(二本)","浙江传媒学院(二本)","浙江财经学院(二本)","浙江警察学院(二本)",

            "浙江大学城市学院(三本)","浙江大学宁波理工学院(三本)", "浙江师范大学行知学院(三本)","[无效]浙江理工大学科技与艺术学院(三本)","杭州电子科技大学信息工程学院(三本)","浙江工商大学杭州商学院(三本)",


    };
    private String[] urls = new String[]{"http://hr.zju.edu.cn/cn/", "http://www.rczp.zjut.edu.cn/", "http://renshi.hdu.edu.cn/", "http://rsc.zjgsu.edu.cn/",
            "http://news.zstu.edu.cn/Col/Col28/Index.aspx/", "http://rsc.hznu.edu.cn/", "http://rsc.cjlu.edu.cn/", "http://zzrsb.caa.edu.cn/",


            "http://210.83.81.227:7013/xinhua_userInforList.asp", "http://rsc.zust.edu.cn/", "http://rsc.zafu.edu.cn/", "http://hr.zjicm.edu.cn/web_rsc/pages/index.php",
            "http://rsc.zufe.edu.cn/", "http://www.zjjcxy.cn/default.html",

            "http://www.zucc.edu.cn/","http://www.nit.net.cn/jsdw/rczp.htm","http://rsc.zjnu.edu.cn/",
            "http://www.ky.zstu.edu.cn/","http://www.hziee.edu.cn/index.php?c=Index&a=index&web=organization","http://rsc.zjgsu.edu.cn/index.asp"
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_uni_list, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < values.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("values", values[i]);
            listItem.put("images", urls[i]);
            list.add(listItem);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
                R.layout.item_uni_list, new String[]{"values", "urls"},
                new int[]{R.id.storeName, R.id.storePic});
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startBrowserActivity(MainActivity.MODE_SONIC_WITH_OFFLINE_CACHE, urls[position]);
            }
        });
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData label = ClipData.newRawUri("Label", Uri.parse(urls[position]));
                mClipboardManager.setPrimaryClip(label);
                mClipboardManager.getPrimaryClip();
                Toast.makeText(getContext(), "网址已复制到粘贴板", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    private void startBrowserActivity(int mode, String url) {
        Intent intent = new Intent(getActivity(), BrowserActivity.class);
        intent.putExtra(BrowserActivity.PARAM_URL, url);
        intent.putExtra(BrowserActivity.PARAM_MODE, mode);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivityForResult(intent, -1);
    }

}
