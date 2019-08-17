package com.example.zhuangguang.module_user.ui.account.mvp;

import android.text.TextUtils;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.net.callback.OnResultObjectCallBack;
import com.example.zhuangguang.module_user.ui.account.mvp.LoginModel;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.LoginContract;

/**
 * @author 庄光
 * @time 2019/7/27  22:33
 * @desc ${TODD}
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model,LoginContract.View> implements LoginContract.Presenter {
    @Override
    protected LoginContract.Model createModule() {
        return new LoginModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void login() {
        if(isViewAttached()) {
            getView().showLoading();
            getModule().login(getView().getUserInfo(), new OnResultObjectCallBack<User>() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, User response) {
                    if (code == 0 && response != null && !TextUtils.isEmpty(String.valueOf(response.getId()))) {
                        getView().loginSuccess(response);
                    } else {
                        getView().onError(tag, msg);
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {
                    getView().onError(tag, msg);
                }

                @Override
                public void onCompleted() {
                    getView().dismissLoading();
                }
            });
        }

    }
}
