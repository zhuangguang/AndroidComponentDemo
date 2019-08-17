package com.example.zhuangguang.common.widget.pulltorefresh;

/**
 * @author 庄光
 * @time 2019/1/28  20:41
 * @desc ${刷新 加载监听}
 */
public interface OnPullRefreshListener {
    /**
     * 刷新
     */
    void onRefresh();

    /**
     * 加载更多
     */
    void onLoadMore();
}
