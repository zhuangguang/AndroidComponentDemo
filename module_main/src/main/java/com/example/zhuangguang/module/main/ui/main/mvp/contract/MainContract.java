package com.example.zhuangguang.module.main.ui.main.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.bean.AppInfo;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/1/30  22:35
 * @desc ${TODD}
 */
public interface MainContract {

    interface Model extends IBaseModel{
        /**
         * 检查更新
         *
         * @param callback 回调函数
         */
        void checkUpdate(OnResultCallBack callback);
    }

    interface View extends IBaseView{
        /**
         * 需要更新
         *
         * @param appInfo appInfo
         */
        void needUpdate(AppInfo appInfo);
        /**
         * 控件tab数据列表
         *
         * @param blockList blockList
         */
        void tabList(List<Template> blockList);
    }

    interface Presenter {
        /**
         * 检查更新
         */
        void checkUpdate();

        /**
         * 获取控件tab数据
         */
        void getTabList();
    }
}
