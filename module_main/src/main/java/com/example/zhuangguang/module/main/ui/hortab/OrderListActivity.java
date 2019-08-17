package com.example.zhuangguang.module.main.ui.hortab;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.zhuangguang.common.base.BaseFragment;
import com.example.zhuangguang.common.base.HorizontalTabActivity;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.common.constants.ARouterConfig;
import com.example.zhuangguang.module.main.ui.hortab.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/14  22:21
 * @desc ${水平滑动Tab}
 */
@Route(path = ARouterConfig.MAIN_ORDER_LIST)
public class OrderListActivity extends HorizontalTabActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleText("我的订单");
    }

    @Override
    protected List<HorizontalTabTitle> getTabTitles() {
        List<HorizontalTabTitle> titles = new ArrayList<>();
        titles.add(new HorizontalTabTitle<>("全部", 0));
        titles.add(new HorizontalTabTitle<>("待支付", 1));
        titles.add(new HorizontalTabTitle<>("待发货", 2));
        titles.add(new HorizontalTabTitle<>("待确认", 3));
        titles.add(new HorizontalTabTitle<>("已失效", -1));
        return titles;
    }

    @Override
    protected BaseFragment getTabFragment() {
        return new OrderFragment();
    }


}
