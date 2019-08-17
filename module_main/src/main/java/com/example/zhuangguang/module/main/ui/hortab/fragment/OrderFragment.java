package com.example.zhuangguang.module.main.ui.hortab.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhuangguang.common.base.RefreshListFragment;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.module.main.R;
import com.example.zhuangguang.module.main.bean.Order;
import com.example.zhuangguang.module.main.ui.hortab.adapter.OrderListAdapter;
import com.example.zhuangguang.module.main.ui.hortab.mvp.OrderPresenter;
import com.example.zhuangguang.module.main.ui.hortab.mvp.contract.OrderContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/14  22:37
 * @desc ${订单列表}
 */
public class OrderFragment extends RefreshListFragment <OrderPresenter> implements OrderContract.View {
    private int page = 0;
    private HorizontalTabTitle title;
    private List<Order> orderList = new ArrayList<>();

    @Override
    public void setFragmentData(HorizontalTabTitle data) {
        super.setFragmentData(data);
        this.title = data;
    }

    @Override
    protected void initView() {
        super.initView();
        initData();
    }

    private void initData() {
        page = 0;
        orderList.clear();
        presenter.start();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onLoadMore() {
        presenter.getOrderList();
    }

    @Override
    public void onError(Object tag, String errorMsg) {
        super.onError(tag, errorMsg);
        if (page == 0) {
            showErrorView();
        } else {
            ToastUtils.showToast(mContext, errorMsg);
        }
    }

    @Override
    public void onEmpty(Object tag) {
        super.onEmpty(tag);
        if (page == 0) {
            showEmptyView();
        } else {
            ToastUtils.showToast(mContext, "暂无更多数据");
        }
    }
    @Override
    protected void onPageClick() {
        super.onPageClick();
        initData();
    }
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new OrderListAdapter(mContext, orderList, R.layout.main_item_of_order_list, this);
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void orderList(List<Order> orders) {
        this.orderList.addAll(orders);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getType() {
        if (title != null) {
            return (int) title.getData();
        }
        return 0;
    }
}
