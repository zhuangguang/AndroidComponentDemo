package com.example.zhuangguang.module.main.ui.hortab.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.module.main.bean.Order;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/14  22:39
 * @desc ${订单契约类}
 */
public interface OrderContract {
    interface Module extends IBaseModel{}


    interface View extends IBaseView{

        /**
         * 订单列表
         */
        void orderList(List<Order> orders);

        /**
         * 分页page
         */
        int getPage();

        /**
         * 订单类型
         */
        int getType();
    }

    interface Presenter {
        /**
         * 获取订单列表
         */
        void getOrderList();
    }
}
