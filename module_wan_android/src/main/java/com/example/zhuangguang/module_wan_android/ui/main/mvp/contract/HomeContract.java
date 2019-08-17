package com.example.zhuangguang.module_wan_android.ui.main.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.bean.BannerInfo;
import com.example.zhuangguang.module_wan_android.bean.WXNumber;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/23  17:12
 * @desc ${wanAndroid首页契约类}
 */
public interface HomeContract {

    interface Model extends IBaseModel{

        /**
         * 获取Banner
         *
         * @param callback 回调
         */
        void getBanner(OnResultCallBack callback);

        /**
         * 获取文章列表
         *
         * @param callback 回调
         */
        void getArticleList(int page, OnResultCallBack callback);

        /**
         * 获取微信公众号
         *
         * @param callback 回调
         */
        void getWXNumber(OnResultCallBack callback);
    }


    interface View extends IBaseView {
        /**
         * banner列表
         */
        void bannerList(List<BannerInfo> banners);

        /**
         * 模块列表
         */
        void blockList(List<Template> blockList);

        /**
         * 返回分页
         */
        int getPage();

        /**
         * 文章列表
         */
        void articleList(List<Article> articles);

        /**
         * 公众号列表
         */
        void wxNumber(List<WXNumber> wxNumbers);
    }


    interface Presenter {
        /**
         * 获取Banner
         */
        void getBanner();

        /**
         * 获取文章列表
         */
        void getArticleList();

        /**
         * 获取微信公众号
         */
        void getWXNumber();

        /**
         * 豆腐块
         */
        void getBlockList();
    }
}
