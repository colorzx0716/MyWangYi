package com.xiao.mywangyi;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class XiangQingActivity extends BaseActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        wv = (WebView) findViewById(R.id.wv);

        //屏幕适配
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        wv.loadUrl(url);
        //用webView打开
      wv.setWebViewClient(new WebViewClient(){
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, String url) {
              view.loadUrl(url);
              return true;
          }
      });
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();//左滑退出activity
    }
}
