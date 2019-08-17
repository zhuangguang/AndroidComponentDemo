package com.example.zhuangguang.module_user.ui.main.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.bean.AppInfo;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;

/**
 * @author 庄光
 * @time 2019/7/27  18:01
 * @desc ${TODD}
 */
public interface UserContract {
    interface  Model extends IBaseModel {

        void checkUpdate(OnResultCallBack callBack);
    }


    interface View extends IBaseView{
        void needUpdate(AppInfo appInfo);

        void isLastVersion();

    }

    interface Presenter {

        void checkUpdate();
    }
}
