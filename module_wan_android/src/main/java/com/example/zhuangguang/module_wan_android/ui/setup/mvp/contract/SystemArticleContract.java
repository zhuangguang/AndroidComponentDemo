package com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract;

import com.example.zhuangguang.common.base.mvp.IBaseModel;
import com.example.zhuangguang.common.base.mvp.IBaseView;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  15:51
 * @desc ${TODD}
 */
public interface SystemArticleContract {

    interface Model extends IBaseModel{
        void getArticle(int page , String id, OnResultCallBack callBack);
    }

    interface View extends IBaseView{
        String getSetupId();

        int getPage();

        void articleList(List<Article> articles);

    }

    interface Presenter{
        void getArtricle();
    }
}
