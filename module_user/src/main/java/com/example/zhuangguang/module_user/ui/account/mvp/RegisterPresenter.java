package com.example.zhuangguang.module_user.ui.account.mvp;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.net.callback.OnResultObjectCallBack;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.RegisterContract;

/**
 * @author 庄光
 * @time 2019/7/27  22:35
 * @desc ${TODD}
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.View>
        implements RegisterContract.Presenter {

    @Override
    public void register() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().register(getView().getUserInfo(), new OnResultObjectCallBack<User>() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, User response) {

                }

                @Override
                public void onFailure(Object tag, Exception e) {

                }

                @Override
                public void onCompleted() {

                }
            });
        }
    }

    @Override
    protected RegisterModel createModule() {
        return new RegisterModel();
    }

    @Override
    public void start() {

    }
}

