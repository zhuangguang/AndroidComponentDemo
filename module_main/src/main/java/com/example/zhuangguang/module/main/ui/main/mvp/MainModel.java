package com.example.zhuangguang.module.main.ui.main.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.RequestParam;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.common.utils.Utils;
import com.example.zhuangguang.module.main.ui.main.mvp.contract.MainContract;

/**
 * @author 庄光
 * @time 2019/1/30  22:34
 * @desc ${TODD}
 */
public class MainModel implements MainContract.Model{
    @Override
    public void checkUpdate(OnResultCallBack callback) {
        RequestParam param = new RequestParam();
        param.addParameter("versionCode", Utils.getVersionCode());
        HttpUtils.getInstance()
                .getRequest(Api.CHECK_UPDATE, param, callback);
    }
}
