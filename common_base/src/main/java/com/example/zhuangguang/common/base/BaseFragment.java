package com.example.zhuangguang.common.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.bean.Event;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.common.widget.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 庄光
 * @time 2019/1/20  17:34
 * @desc ${所有Fragment的基类}
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment{
    private ViewStub emptyView;
    private View rootView;
    private Unbinder unBinder;
    protected Context mContext;
    protected LoadingDialog loadingDialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        loadingDialog = new LoadingDialog(mContext);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false);
        ((ViewGroup) rootView.findViewById(R.id.fl_content)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        unBinder = ButterKnife.bind(this, rootView);
        if (regEvent()) {
            EventBus.getDefault().register(this);
        }
        return rootView;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent()) {
            EventBus.getDefault().unregister(this);
        }
    }

    //***************************************空页面方法*************************************
    protected void showEmptyView() {
        showEmptyOrErrorView(getString(R.string.no_data), R.drawable.bg_no_data);
    }


    protected void showErrorView() {
        showEmptyOrErrorView(getString(R.string.error_data), R.drawable.bg_no_net);
    }

    public void showEmptyOrErrorView(String text, int img) {
        emptyView = rootView.findViewById(R.id.vs_empty);

        if (emptyView != null) {
            emptyView.setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.iv_empty).setBackgroundResource(img);
            ((TextView) rootView.findViewById(R.id.tv_empty)).setText(text);
            rootView.findViewById(R.id.ll_empty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPageClick();
                }
            });
        }
    }

    protected void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 空页面被点击
     */
    protected void onPageClick() {

    }

    //***************************************空页面方法*********************************

    /**
     * 给Fragment设置数据
     */
    public void setFragmentData(HorizontalTabTitle data) {

    }

    /**
     * 子类接受事件 重写该方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Event event) {
    }

    /**
     * 需要接收事件 重新该方法 并返回true
     */
    protected boolean regEvent() {
        return false;
    }


    protected abstract int getLayoutId();

    protected abstract void initView();
}
