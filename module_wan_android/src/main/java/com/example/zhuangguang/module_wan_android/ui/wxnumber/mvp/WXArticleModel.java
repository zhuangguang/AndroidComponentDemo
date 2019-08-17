package com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp.contract.WXNumberContract;

/**
 * @author 庄光
 * @time 2019/8/4  17:12
 * @desc ${TODD}
 */
public class WXArticleModel implements WXNumberContract.Model {


    @Override
    public void getWXArticle(int id, int page, OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(String.format(Api.WX_ARTICLE_LIST, id, page), callback);
    }
}
