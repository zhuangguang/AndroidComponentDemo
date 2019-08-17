package com.example.zhuangguang.module_wan_android.ui.collection.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.collection.mvp.contract.CollectionContract;

/**
 * @author 庄光
 * @time 2019/8/17  17:20
 * @desc ${TODD}
 */
public class CollectionModel implements CollectionContract.Model {


    @Override
    public void getCollectionList(int page, OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(String.format(Api.COLLECTION_LIST, page), callback);
    }
}