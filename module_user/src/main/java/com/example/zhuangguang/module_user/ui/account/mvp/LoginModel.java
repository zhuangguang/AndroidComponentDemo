package com.example.zhuangguang.module_user.ui.account.mvp;

import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.RequestParam;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.LoginContract;
import com.tamic.novate.Throwable;

/**
 * @author 庄光
 * @time 2019/7/27  22:20
 * @desc ${TODD}
 */
public class LoginModel  implements LoginContract.Model{
    @Override
    public void login(User user, OnResultCallBack back) {
        if(user == null) {
            back.onError("", (Throwable) new Exception("用户信息为空"));
            RequestParam param = new RequestParam();
            param.addParameter("username", user.getUsername());
            param.addParameter("password", user.getPassword());
            HttpUtils.getInstance()
                    .postRequest(Api.LOGIN, param, back);
        }
    }
}
