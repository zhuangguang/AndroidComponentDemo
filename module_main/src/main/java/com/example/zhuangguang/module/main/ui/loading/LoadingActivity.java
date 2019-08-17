package com.example.zhuangguang.module.main.ui.loading;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.zhuangguang.common.base.BaseActivity;
import com.example.zhuangguang.module.main.R;
import com.example.zhuangguang.module.main.ui.main.MainActivity;

/**
 * @author 庄光
 * @time 2019/1/30  22:10
 * @desc ${应用启动页}
 */
public class LoadingActivity extends BaseActivity{
    private long loadingTime = 500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mHandler.sendEmptyMessageDelayed(0, loadingTime);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(mContext, MainActivity.class));
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_loading;
    }

    @Override
    protected void initView() {

    }
}
