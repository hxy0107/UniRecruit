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

    private String[] values = new String[]{"武汉大学", "华中科技大学", "[无效]华中师范大学", "武汉理工大学", "中国地质大学 (武汉)", "华中农业大学", "中南财经政法大学", "湖北大学", "武汉科技大学",

            "[无效]武汉工程大学(二本)", "武汉纺织大学(二本)", "武汉轻工大学(二本)", "湖北工业大学(二本)", "湖北中医药大学(二本)", "[无效]武汉体育学院(二本)", "湖北美术学院(二本)", "中南民族大学(二本)",
            "江汉大学(二本)", "湖北警官学院(二本)", "武汉音乐学院(二本)", "湖北经济学院(二本)", "[无效]武汉商学院(二本)",

            "武汉科技大学城市学院(三本)", "武汉理工大学华夏学院(三本)", "武汉大学珞珈学院(三本)", "华中师范大学武汉传媒学院(三本)", "湖北经济学院法商学院(三本)", "湖北大学知行学院(三本)", "江汉大学文理学院(三本)",
            "湖北工业大学工程技术学院(三本)", "武汉工程大学邮电与信息工程学院(三本)", "武汉纺织大学外经贸学院(三本)", "武汉体育学院体育科技学院(三本)"

            , "武昌首义学院(民办三本)", "文华学院(民办三本)", "武汉工商学院(民办三本)", "武汉东湖学院(民办三本)", "武昌理工学院(民办三本)", "武汉学院(民办三本)",
            "汉口学院(民办三本)", "武汉工程科技学院(民办三本)", "武汉生物工程学院(民办三本)", "武昌工学院(民办三本)", "湖北商贸学院(民办三本)", "武汉设计工程学院(民办三本)"


    };
    private String[] urls = new String[]{"http://hr.whu.edu.cn/", "http://employment.hust.edu.cn/", "http://zhaopin.whut.edu.cn/", "http://hr.ccnu.edu.cn",
            "http://www.cug.edu.cn/", "http://rsc.hzau.edu.cn/zpygs/zpxx/", "http://rsb.zuel.edu.cn/1276/list.htm", "http://renshi.hubu.edu.cn", "http://rsc.wust.edu.cn/",


            "http://zzrsb.wit.edu.cn/", "http://rsc.wtu.edu.cn/", "http://rsc.whpu.edu.cn/", "http://rsc.hbut.edu.cn/", "http://rsc.hbtcm.edu.cn/",
            "http://wipe.edu.cn", "http://rsc.hifa.edu.cn/", "http://www.scuec.edu.cn/zzrsb/", "http://rsc.jhun.edu.cn/2435/list.htm", "http://www.hbpa.edu.cn/rsgk.htm",
            "http://rsc.whcm.edu.cn/", "http://rsc.hbue.edu.cn/", "http://wbu.edu.cn/"

            , "http://www.city.wust.edu.cn/", "http://www.hxut.edu.cn/plus/list.php?tid=1233", "http://rsc.luojia-whu.cn/index.php/Index-index.html", "http://rs.whmc.edu.cn/rczp/",
            "http://fsxy.hbue.edu.cn/", "http://rzb.hudazx.cn/rcyj.htm", "http://rsb.jdwlxy.cn/index.html", "http://gcxy.hbut.edu.cn/", "http://www.witpt.edu.cn/", "http://cibe.wtu.edu.cn/", "http://kjxy.whsu.edu.cn/xwgk/rssz.htm"

            , "http://rsc.wsyu.edu.cn/", "http://www.hustwenhua.net/", "http://www.wtbu.cn/rsb", "http://www.wdu.edu.cn/gljg/rsc/", "http://rsc.wut.edu.cn/", "http://rs.whxy.edu.cn/",
            "http://hr.hkxy.edu.cn/", "http://rsc.wuhues.com/", "http://rsc.whsw.cn/", "http://rsc.wuit.cn/", "http://www.hugsmxy.com/", "http://www.hnctxy.com/"
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