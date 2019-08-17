package com.example.zhuangguang.module_user.ui.account.mvp;

import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.RequestParam;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.RegisterContract;

/**
 * @author 庄光
 * @time 2019/7/27  22:31
 * @desc ${TODD}
 */
public class RegisterModel implements RegisterContract.Model{
    @Override
    public void register(User user, OnResultCallBack callback) {
        if (user == null)
            callback.onError("",null);
        RequestParam param = new RequestParam();
        param.addParameter("username", user.getUsername());
        param.addParameter("password", user.getPassword());
        param.addParameter("repassword", user.getPassword());
        HttpUtils.getInstance()
                .postRequest(Api.REGISTER, param, callback);
    }
}
