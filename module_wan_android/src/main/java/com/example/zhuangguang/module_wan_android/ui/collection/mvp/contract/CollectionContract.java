package com.example.zhuangguang.module_wan_android.ui.collection.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/17  17:19
 * @desc ${TODD}
 */
public interface CollectionContract {
    interface Model extends IBaseModel {
        /**
         * 获取收藏列表
         *
         * @param page     分页
         * @param callback 回调
         */
        void getCollectionList(int page, OnResultCallBack callback);
    }

    interface View extends IBaseView {
        /**
         * 获取分页
         */
        int getPage();

        /**
         * 收藏列表
         *
         * @param collections collections
         */
        void collectionList(List<Article> collections);
    }

    interface Presenter {
        /**
         * 获取我的收藏列表
         */
        void getCollectionList();
    }
}
