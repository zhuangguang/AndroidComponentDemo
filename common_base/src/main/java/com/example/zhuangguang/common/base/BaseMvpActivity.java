package com.example.zhuangguang.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.base.mvp.IBaseView;

/**
 * @author 庄光
 * @time 2019/1/20  17:26
 * @desc ${TODD}
 */
public abstract class  BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView{
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }

    }
    //***************************************IBaseView方法实现*************************************
    @Override
    public void showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }
    @Override
    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }
    //***************************************IBaseView方法实现*************************************

    protected abstract P createPresenter();

}
