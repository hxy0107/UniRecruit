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
 * Created by 毕空 on 2017/10/23.
 */

public class UniListFragment extends ListFragment {

    private ClipboardManager mClipboardManager;

    private String[] values = new String[]{"武汉大学", "华中科技大学", "华中师范大学", "武汉理工大学", "中国地质大学 (武汉)", "华中农业大学", "中南财经政法大学", "湖北大学", "武汉科技大学"};
    private String[] urls = new String[]{"http://hr.whu.edu.cn/", "http://employment.hust.edu.cn/", "http://zhaopin.whut.edu.cn/", "http://hr.ccnu.edu.cn",
            "http://www.cug.edu.cn/", "http://rsc.hzau.edu.cn/zpygs/zpxx/", "http://rsb.zuel.edu.cn/1276/list.htm", "http://renshi.hubu.edu.cn", "http://rsc.wust.edu.cn/"};


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