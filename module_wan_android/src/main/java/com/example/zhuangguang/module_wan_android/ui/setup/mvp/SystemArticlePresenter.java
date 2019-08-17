package com.example.zhuangguang.module_wan_android.ui.setup.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.ui.setup.mvp.contract.SystemArticleContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  16:06
 * @desc ${TODD}
 */
public class SystemArticlePresenter extends BasePresenter<SystemArticleContract.Model, SystemArticleContract.View>
        implements SystemArticleContract.Presenter {
    @Override
    protected SystemArticleContract.Model createModule() {
        return new SystemArticleModel();
    }

    @Override
    public void start() {
        getArtricle();
    }

    @Override
    public void getArtricle() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getArticle(getView().getPage(), getView().getSetupId(), new OnResultStringCallBack() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, String response) {
                    if (code == Constants.SUCCESS_CODE) {
                        List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                        if (articleList == null || articleList.size() < 1) {
                            getView().onEmpty(tag);
                        } else {
                            getView().articleList(articleList);
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
}
