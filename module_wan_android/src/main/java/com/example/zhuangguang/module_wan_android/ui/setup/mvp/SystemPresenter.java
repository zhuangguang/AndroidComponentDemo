package com.example.zhuangguang.module_wan_android.ui.setup.mvp;

import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultListCallBack;
import com.example.zhuangguang.module_wan_android.bean.Classification;
import com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract.SystemContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  16:04
 * @desc ${TODD}
 */
public class SystemPresenter  extends BasePresenter<SystemContract.Model, SystemContract.View>
        implements SystemContract.Presenter {
    @Override
    protected SystemContract.Model createModule() {
        return new SystemModel();
    }

    @Override
    public void start() {
        getSystem();
    }

    @Override
    public void getSystem() {
        getView().showLoading();
        getModule().getSystem(new OnResultListCallBack<List<Classification>>() {
            @Override
            public void onSuccess(boolean success, int code, String msg, Object tag, List<Classification> response) {
                if (code == 0) {
                    if (response == null || response.size() < 1) {
                        getView().onEmpty(tag);
                    } else {
                        getView().systemList(response);
                    }
                } else {
                    getView().onError(tag, msg);
                }
            }

            @Override
            public void onFailure(Object tag, Exception e) {
                getView().onError(tag, Constants.ERROR_MESSAGE);
            }

            @Override
            public void onCompleted() {
                getView().dismissLoading();
            }
        });
    }
}
