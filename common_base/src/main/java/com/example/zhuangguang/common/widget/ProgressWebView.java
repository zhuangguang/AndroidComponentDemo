package com.example.zhuangguang.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.zhuangguang.common.widget.dialog.LoadingDialog;

/**
 * @author 庄光
 * @time 2019/2/12  21:21
 * @desc ${TODD}
 */
public class ProgressWebView extends WebView {
    private WebViewProgressBar progressBar;
    private Handler handler;
    private WebView _this;
    private LoadingDialog loadingDialog;

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressWebView(Context context) {
        super(context);
        progressBar = new WebViewProgressBar(context);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        progressBar.setVisibility(GONE);
        addView(progressBar);
        handler = new Handler();
        _this = this;
        loadingDialog = new LoadingDialog(context);
        loadingDialog.setCancelable(true);

        setWebChromeClient(new MyWebChromeClient());

        /**
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }

        /** 设置webView不显示图片问题 */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBlockNetworkImage(false);


    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showDialog(String text){
        if(!TextUtils.isEmpty(text)) {
            loadingDialog.setTitleText("正在加载...");
        }
        loadingDialog.show();
    }

    private class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress == 100) {
                progressBar.setProgress(100);
                handler.postDelayed(runnable,200);
            }else if(progressBar.getVisibility() ==  GONE){
                progressBar.setVisibility(VISIBLE);
            }
            if(newProgress < 5) {
                newProgress = 5;
            }
            progressBar.setProgress(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dismissDialog();
        }
    };

    public void dismissDialog() {
        if(loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        progressBar.setVisibility(GONE);
    }
}
