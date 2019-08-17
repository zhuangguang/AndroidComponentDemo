package com.example.zhuangguang.module_wan_android.ui.search.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:26
 * @desc ${TODD}
 */
public interface SearchContract {

    interface Module extends IBaseModel{

        void searchData(int page, String word, OnResultCallBack callback);
    }

    interface View extends IBaseView{
        String getWord();

        int getPage();

        void searchData(List<Article> searchs);
    }

    interface Presenter {

        /**
         * 搜索
         */
        void search();
    }
}


