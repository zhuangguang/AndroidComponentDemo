package com.example.zhuangguang.module_wan_android.ui.setup.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract.SystemContract;

/**
 * @author 庄光
 * @time 2019/8/3  11:59
 * @desc ${TODD}
 */
public class SystemModel implements SystemContract.Model {
    @Override
    public void getSystem(OnResultCallBack callBack) {
        HttpUtils.getInstance()
                .getRequest(Api.TREE, callBack);
    }
}
