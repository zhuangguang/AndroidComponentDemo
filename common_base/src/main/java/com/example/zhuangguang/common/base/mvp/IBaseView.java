package com.example.zhuangguang.common.base.mvp;

import android.content.Context;

/**
 * @author 庄光
 * @time 2019/1/20  10:40
 * @desc ${所有View基类}
 */
public interface IBaseView {

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void dismissLoading();

    /**
     * 空数据
     * @param tag
     */
    void  onEmpty(Object tag);

    /**
     * 错误数据
     * @param tag
     * @param errorMsg
     */
    void onError(Object tag, String errorMsg);

    /**
     * 上下文
     * @return
     */
    Context getContext();

}
