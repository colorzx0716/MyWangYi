package com.xiao.mywangyi;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 张肖肖 on 2017/9/13.
 */

public class MyApp extends Application {
    { PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //友盟
        UMShareAPI.get(this);

        //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //夜间模式
       if (getSharedPreferences("theme",MODE_PRIVATE).getBoolean("night_theme",false)){
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
       }


        x.Ext.init(this);
        x.Ext.setDebug(false);

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
