package com.example.zhuangguang.module_wan_android.ui.wxnumber.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhuangguang.common.base.RefreshListFragment;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.common.utils.ActivityToActivity;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.bean.WXNumber;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.adapter.WXArticleAdapter;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp.WXArticlePresenter;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp.contract.WXNumberContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:12
 * @desc ${TODD}
 */
public class WXArticleFragment extends RefreshListFragment<WXArticlePresenter> implements WXNumberContract.View {


    private List<Article> wxArticles = new ArrayList<>();
    private WXNumber wxNumber;
    private int page = 1;

    @Override
    protected void initView() {
        super.initView();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        wxArticles.clear();
        presenter.start();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.start();
    }

    @Override
    public void onItemClick(View view, int position) {
        ActivityToActivity.toWebView(mContext, wxArticles.get(position).getLink());
    }

    @Override
    protected WXArticlePresenter createPresenter() {
        return new WXArticlePresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new WXArticleAdapter(mContext, wxArticles, R.layout.wan_item_of_wx_article_list, this);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setFragmentData(HorizontalTabTitle data) {
        super.setFragmentData(data);
        wxNumber = (WXNumber) data.getData();
    }

    @Override
    public int getWXNumberId() {
        return wxNumber.getId();
    }

    @Override
    public void wxArticleList(List<Article> wxArticles) {
        this.wxArticles.addAll(wxArticles);
        adapter.notifyDataSetChanged();
    }
}

