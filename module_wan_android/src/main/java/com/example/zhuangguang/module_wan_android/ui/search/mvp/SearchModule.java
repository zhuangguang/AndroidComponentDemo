package com.example.zhuangguang.module_wan_android.ui.search.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.search.mvp.contract.SearchContract;

/**
 * @author 庄光
 * @time 2019/8/4  17:26
 * @desc ${TODD}
 */
public class SearchModule implements SearchContract.Module{
    @Override
    public void searchData(int page, String word, OnResultCallBack callback) {
        HttpUtils.getInstance()
                .postRequest(String.format(Api.SEARCH_LIST, page, word), callback);
    }
}
