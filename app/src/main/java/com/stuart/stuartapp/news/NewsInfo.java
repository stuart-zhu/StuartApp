package com.stuart.stuartapp.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

/**
 * Created by stuart on 2017/8/22.
 */

public class NewsInfo extends BaseActivity {

    private String url;

    private WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        mWebView = (WebView) findViewById(R.id.wv);
        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        if(getIntent() != null) {
           url = getIntent().getStringExtra("url");
            mWebView.loadUrl(url);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(mWebView.canGoBack())
            {
                mWebView.goBack();//返回上一页面
                return true;
            }
            else
            {
               // System.exit(0);//退出程序
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
