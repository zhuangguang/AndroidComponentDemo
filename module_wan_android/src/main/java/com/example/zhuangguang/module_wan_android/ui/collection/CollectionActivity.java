package com.example.zhuangguang.module_wan_android.ui.collection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.zhuangguang.common.base.RefreshListActivity;
import com.example.zhuangguang.common.constants.ARouterConfig;
import com.example.zhuangguang.common.utils.ActivityToActivity;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.ui.collection.mvp.CollectionPresenter;
import com.example.zhuangguang.module_wan_android.ui.collection.mvp.contract.CollectionContract;
import com.example.zhuangguang.module_wan_android.ui.project.adapter.ProjectListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/17  17:21
 * @desc ${TODD}
 */
@Route(path = ARouterConfig.WAN_COLLECTION)
public class CollectionActivity extends RefreshListActivity<CollectionPresenter> implements CollectionContract.View {

    private int page = 0;

    private List<Article> collectionList = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();
        setTitleText("我的收藏");
        presenter.getCollectionList();
    }

    @Override
    public void onRefresh() {
        page = 0;
        collectionList.clear();
        presenter.start();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getCollectionList();
    }

    @Override
    public void onItemClick(View view, int position) {
        ActivityToActivity.toWebView(mContext, collectionList.get(position).getLink());
    }

    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new ProjectListAdapter(mContext, collectionList, R.layout.wan_item_of_project_list, this);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void collectionList(List<Article> collections) {
        this.collectionList.addAll(collections);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEmpty(Object tag) {
        super.onEmpty(tag);
        if (page == 0) {
            showEmptyView("您还没有收藏任何东西");
        } else {
            ToastUtils.showToast(mContext, "暂无更多收藏~");
        }
    }

    @Override
    public void onError(Object tag, String msg) {
        super.onEmpty(tag);
        if (page == 0) {
            showEmptyView(msg);
        } else {
            ToastUtils.showToast(mContext, msg);
        }
    }
}
