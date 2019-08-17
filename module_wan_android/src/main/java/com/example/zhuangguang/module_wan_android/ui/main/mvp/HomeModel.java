package com.example.zhuangguang.module_wan_android.ui.main.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.main.mvp.contract.HomeContract;

/**
 * @author 庄光
 * @time 2019/2/23  17:24
 * @desc ${首页Module}
 */
public class HomeModel implements HomeContract.Model {
    @Override
    public void getBanner(OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(Api.GET_BANNER_LIST,callback);
    }

    @Override
    public void getArticleList(int page, OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(String.format(Api.GET_ARTICLE_LIST,page),callback);

    }

    @Override
    public void getWXNumber(OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(Api.WX_NUMBER , callback);

    }
}
