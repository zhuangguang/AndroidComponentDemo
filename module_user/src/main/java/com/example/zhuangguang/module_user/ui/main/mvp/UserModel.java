package com.example.zhuangguang.module_user.ui.main.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.RequestParam;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.common.utils.Utils;
import com.example.zhuangguang.module_user.ui.main.mvp.contract.UserContract;

/**
 * @author 庄光
 * @time 2019/7/27  18:11
 * @desc ${TODD}
 */
public class UserModel implements UserContract.Model {
    @Override
    public void checkUpdate(OnResultCallBack callBack) {
        RequestParam requestParam = new RequestParam();
        requestParam.addParameter("versionCode", Utils.getVersionCode());
        HttpUtils.getInstance()
                .getRequest(Api.CHECK_UPDATE,requestParam,callBack);
    }
}
