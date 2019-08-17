package com.example.zhuangguang.common.widget.pulltorefresh;

import android.view.View;

/**
 * @author 庄光
 * @time 2019/1/28  20:42
 * @desc ${刷新view}
 */
public interface IRefreshView {
    /**
     * 开始下拉
     */
    void begin();

    /**
     * 回调的精度,单位为px
     *
     * @param progress 当前高度
     * @param all      总高度   为默认高度的2倍
     */
    void progress(float progress, float all);

    /**
     * 下拉完毕
     */
    void finishing(float progress, float all);

    void loading();
    /**
     * 看不见的状态
     */
    void normal();
    /**
     * 返回当前视图
     */
    View getView();

    void setType(boolean refresh);
}
