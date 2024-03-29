package com.example.zhuangguang.module.main.ui.main.mvp;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.AppInfo;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.constants.ARouterConfig;
import com.example.zhuangguang.common.net.callback.OnResultObjectCallBack;
import com.example.zhuangguang.module.main.R;
import com.example.zhuangguang.module.main.ui.hortab.OrderListActivity;
import com.example.zhuangguang.module.main.ui.main.mvp.contract.MainContract;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/1/30  22:34
 * @desc ${首页Presenter}
 */
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> implements MainContract.Presenter{
    @Override
    protected MainContract.Model createModule() {
        return new MainModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void checkUpdate() {
        if(isViewAttached()) {
            getModule().checkUpdate(new OnResultObjectCallBack<AppInfo>() {
                @Override
                public void onNext(Object tag, int code, String message, AppInfo response) {

                }

                @Override
                protected void onSuccess(boolean success, int code, String msg, Object tag, AppInfo response) {
                    if (code == 1000 || response != null) {
                        //需要更新
                        getView().needUpdate(response);
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {
                    Logger.e(e.getMessage());
                }

                @Override
                protected void onCompleted() {

                }
            });

        }

    }

    @Override
    public void getTabList() {
        if (isViewAttached()) {
            //造伪数据
            List<Template> list = new ArrayList<>();
            list.add(new Template("商城", R.drawable.main_icon_17, ARouterConfig.MARKET_MAIN_ACTIVITY, 1, "电商商品模板，和JD相似"));
            list.add(new Template("水平Tab", R.drawable.main_icon_8, OrderListActivity.class, "水平滑动选项卡"));
//            list.add(new Template("下拉刷新", R.drawable.main_icon_9, RefreshStringActivity.class, "下拉刷新，上拉加载更多，可自定义加载View"));
//            list.add(new Template("多功能选择器", R.drawable.main_icon_10, SelectorActivity.class, "时间、日期、地址、自定义数据滑动选择器"));
//            list.add(new Template("数量加减控件", R.drawable.main_icon_11, CountViewActivity.class, "数量加减、支持手动输入"));
//            list.add(new Template("多功能横向Item", R.drawable.main_icon_12, MultipleItemActivity.class, "左右文字+Icon的Item  支持右输入框显示"));
//            list.add(new Template("观察者按钮", R.drawable.main_icon_13, ObserverButtonActivity.class, "利用按钮对多个输入框监听，如：注册"));
//            list.add(new Template("多功能对话框", R.drawable.main_icon_14, DialogActivity.class, "常用对话框，支持输入 单按钮等"));
//            list.add(new Template("流式布局", R.drawable.main_icon_15, FlowLayoutActivity.class, "流式布局，从左上角位置开始，自动换行"));
//            list.add(new Template("聊天", R.drawable.main_icon_16, IMActivity.class, "聊天布局样式，用于测试多功能列表适配器"));
//            list.add(new Template("图片查看器", R.drawable.main_icon_18, ScaleImageActivity.class, "大图查看器、支持下载、删除、缩放等操作"));
            getView().tabList(list);
        }
    }
}
