package com.example.zhuangguang.module_user.ui.account.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;

/**
 * @author 庄光
 * @time 2019/7/27  22:21
 * @desc ${TODD}
 */
public interface LoginContract {

    interface Model extends IBaseModel{
        void login(User user, OnResultCallBack back);
    }


    interface View extends IBaseView{
        User getUserInfo();

        void loginSuccess(User user);

    }

    interface Presenter{
        void login();
    }
}
