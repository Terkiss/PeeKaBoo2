package com.terukiss.peekaboo2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.terukiss.peekaboo2.R;

public class AnimationWebView extends AppCompatActivity {

    WebView mWebView ; // 웹뷰 선언
    WebSettings mWebSetting; // 웹뷰 세팅
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_web_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mWebView = findViewById(R.id.animationView);

        mWebView.getSettings().setJavaScriptEnabled(true); // 웹뷰 자바스크립트 허용
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.getSettings().setAllowFileAccess(true); // -> xss 취약점 떔에 false 추천

        mWebView.getSettings().setAllowFileAccessFromFileURLs(true); // 다른 도메인도 허용

        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);


        mWebView.loadUrl("https://panogas.com/");// 웹뷰 실행

        mWebView.setWebChromeClient(new FullscreenableChromeClient(AnimationWebView.this)); // 웹뷰에서 크롬이 실행 가능하도록 열어둠
        mWebView.setWebViewClient(new WebViewClientClass());

//        if(savedInstanceState != null) // 객체 안에 값이 저장되어 있다면?
//        {
//            String url = savedInstanceState.getString("url");
//            mWebView.loadUrl(url);
//        }

    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("url", mWebView.getUrl());
//    }

    public boolean onKeyDown(int KeyCode, KeyEvent event)
    {// 뒤로 가기 버튼 입력시
        if( (KeyCode ==  KeyEvent.KEYCODE_BACK) &&  mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(KeyCode, event);
    }
    class WebViewClientClass extends WebViewClient
    {
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            Log.d("Check Url", url);
            view.loadUrl(url);
            return true;
        }

    }
}
