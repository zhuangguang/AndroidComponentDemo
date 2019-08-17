package com.example.zhuangguang.common.base;

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.R2;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.listener.OnListItemClickListener;
import com.example.zhuangguang.common.widget.pulltorefresh.OnPullRefreshListener;
import com.example.zhuangguang.common.widget.pulltorefresh.PullToRefreshLayout;

import butterknife.BindView;

/**
 * @author 庄光
 * @time 2019/1/22  23:10
 * @desc ${ibe：带下拉刷新 上拉加载更多的Activity
 * 内部实现为刷新控件 PullToRefreshLayout + 列表控件 RecyclerView}
 */
public abstract class RefreshListActivity <P extends BasePresenter> extends ActionBarActivity<P> implements OnPullRefreshListener , OnListItemClickListener {
    @BindView(R2.id.ptrl_list)
    PullToRefreshLayout refreshLayout;

    @BindView(R2.id.recycle_view)
    RecyclerView recyclerView;

    protected RecyclerView.Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_refresh;
    }

    @CallSuper
    @Override
    protected void initView() {
        refreshLayout.setOnPullRefreshListener(this);
        recyclerView.setLayoutManager(getLayoutManager());
        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
    }

    @CallSuper
    @Override
    public void dismissLoading() {
        super.dismissLoading();
        stopRefresh();
    }
    @CallSuper
    @Override
    public void showLoading() {
        super.showLoading();
        hideEmptyView();
    }

    /**
     * 停止刷新
     */
    protected void stopRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }


    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract RecyclerView.Adapter createAdapter();
}
