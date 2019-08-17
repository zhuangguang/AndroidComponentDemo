package com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.mvp.contract.WXNumberContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:18
 * @desc ${TODD}
 */
public class WXArticlePresenter extends BasePresenter<WXNumberContract.Model, WXNumberContract.View>
        implements WXNumberContract.Presenter {


    @Override
    public void getWXArticle() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getWXArticle(getView().getWXNumberId(), getView().getPage(), new OnResultStringCallBack() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, String
                        response) {
                    if (code == Constants.SUCCESS_CODE) {
                        final List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                        if (articleList == null || articleList.size() < 1) {
                            getView().onEmpty(tag);
                        } else {
                            getView().wxArticleList(articleList);
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

    @Override
    protected WXArticleModel createModule() {
        return new WXArticleModel();
    }

    @Override
    public void start() {
        getWXArticle();
    }
}
