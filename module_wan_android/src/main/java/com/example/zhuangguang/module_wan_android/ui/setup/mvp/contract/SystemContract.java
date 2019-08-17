package com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Classification;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/3  12:01
 * @desc ${TODD}
 */
public interface SystemContract {

    interface Model extends IBaseModel{

        void getSystem(OnResultCallBack callBack);
    }

    interface View extends IBaseModel,IBaseView{
        void systemList(List<Classification> systems);
    }

    interface Presenter{
        void getSystem();
    }
}
