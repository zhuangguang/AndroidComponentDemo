package com.example.zhuangguang.module_user.ui.main.mvp;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.AppInfo;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultObjectCallBack;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.module_user.ui.main.mvp.contract.UserContract;

/**
 * @author 庄光
 * @time 2019/7/27  18:00
 * @desc ${TODD}
 */
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> implements UserContract.Presenter{
    @Override
    protected UserContract.Model createModule() {
        return new UserModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void checkUpdate() {
        if(isViewAttached()) {
            getView().showLoading();
            getModule().checkUpdate(new OnResultObjectCallBack<AppInfo>() {

                @Override
                protected void onSuccess(boolean success, int code, String msg, Object tag, AppInfo response) {
                    if (code == 1000 || response != null) {
                        //需要更新
                        getView().needUpdate(response);
                    } else {
                        getView().isLastVersion();
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {
                    ToastUtils.showToast(getContext(), Constants.ERROR_MESSAGE);
                }

                @Override
                protected void onCompleted() {
                    getView().dismissLoading();
                }
            });
        }

    }
}
