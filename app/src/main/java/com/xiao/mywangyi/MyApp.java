package com.xiao.mywangyi;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 张肖肖 on 2017/9/13.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initIMG();
    }

    //图片下载方法
    private void initIMG() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration cofig = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();

        ImageLoader.getInstance().init(cofig);

    }
}
