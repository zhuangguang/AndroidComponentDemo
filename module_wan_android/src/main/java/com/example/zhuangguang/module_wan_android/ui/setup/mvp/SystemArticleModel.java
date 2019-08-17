package com.example.zhuangguang.module_wan_android.ui.setup.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract.SystemArticleContract;

/**
 * @author 庄光
 * @time 2019/8/4  16:03
 * @desc ${TODD}
 */
public class SystemArticleModel implements SystemArticleContract.Model {
    @Override
    public void getArticle(int page, String id, OnResultCallBack callBack) {
        HttpUtils.getInstance()
                .getRequest(String.format(Api.TREE_ARTICLE, page, id), callBack);
    }
}
