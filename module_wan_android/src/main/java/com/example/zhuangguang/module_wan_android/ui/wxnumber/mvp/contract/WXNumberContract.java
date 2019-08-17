package com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:15
 * @desc ${TODD}
 */
public interface WXNumberContract {
    interface Model extends IBaseModel {
        /**
         * 获取公众号文章列表
         *
         * @param id       公众号ID
         * @param page     页码
         * @param callback 回调
         */
        void getWXArticle(int id, int page, OnResultCallBack callback);

    }

    interface View extends IBaseView {
        /**
         * 返回页码
         */
        int getPage();

        /**
         * 公众号ID
         */
        int getWXNumberId();

        /**
         * 公众号文章类表
         */
        void wxArticleList(List<Article> wxArticles);
    }

    interface Presenter {
        /**
         * 获取公众号文章
         */
        void getWXArticle();
    }
}