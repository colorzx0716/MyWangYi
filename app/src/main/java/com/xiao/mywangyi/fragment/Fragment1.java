package com.xiao.mywangyi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xiao.mywangyi.R;
import com.xiao.mywangyi.adapter.MyListAdapter;
import com.xiao.mywangyi.bean.Bean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import view.xlistview.XListView;

/**
 * Created by 张肖肖 on 2017/9/13.
 */

public class Fragment1 extends Fragment implements XListView.IXListViewListener {
    private String url = "http://api.tianapi.com/health/?key=5d24f9f2147166cca89a4e4455afa72c&num=10";

    private View mRootview;
    private ViewPager f_vp;
    private XListView xlv;
    private List<Bean.NewslistBean> newslist;
    private MyListAdapter adapter;

    private String[] imgurl = {"http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/84f768716d224053a72a37f8291716d620170908122708.png_150x100x1x85.jpg",
            "http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/b2ab2177dd084b01a3a9e0745f54c0c220170911094949.png_150x100x1x85.jpg",
            "http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/03f696519c9b4f97900a64c5780ce38120170907121034.png_150x100x1x85.jpg",
            "http://imgsize.ph.126.net/?imgurl=http://cms-bucket.nosdn.127.net/57a5a6f7ff0d42e4a6930e903cbcefb920170906123404.png_150x100x1x85.jpg"};

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int currentItem = f_vp.getCurrentItem();
            currentItem++;
            f_vp.setCurrentItem(currentItem);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        x.view().inject(getActivity());

        mRootview = LayoutInflater.from(getActivity()).inflate(R.layout.fragment1, null);

        f_vp = mRootview.findViewById(R.id.f_vp);
        xlv = mRootview.findViewById(R.id.xlv);
        xlv.setXListViewListener(this);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);

        //无限轮播和无线轮播  3s
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        timer.schedule(task,0,3000);

        f_vp.setAdapter(new MyPagerAdapter());



        //解析数据
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username","abc");
        params.addQueryStringParameter("password","123");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                //解析数据
                System.out.println("请求的数据 = " + result);
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                newslist = bean.getNewslist();

                //适配器
                adapter = new MyListAdapter(getActivity(),newslist);
                xlv.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        return mRootview;
    }

    //上拉加载下拉刷新
    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
        newslist.addAll(newslist);
        adapter.notifyDataSetChanged();
        xlv.stopLoadMore();
        xlv.stopRefresh();

    }

    @Override
    public void onLoadMore() {
        Toast.makeText(getActivity(), "没有更多数据了，再看看其他的吧", Toast.LENGTH_SHORT).show();
        xlv.stopLoadMore();
        xlv.stopRefresh();

    }


    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(getActivity(), R.layout.vp_item, null);
            ImageView vp_imgs = (ImageView) view.findViewById(R.id.vp_imgs);
            ImageLoader.getInstance().displayImage(imgurl[position%imgurl.length],vp_imgs);
            container.addView(view);
            return view;
        }
    }
}
