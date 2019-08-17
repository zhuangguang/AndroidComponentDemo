package com.example.zhuangguang.module_wan_android.ui.search.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.ui.search.mvp.contract.SearchContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/17  17:01
 * @desc ${TODD}
 */
public class SearchPresenter extends BasePresenter<SearchContract.Module, SearchContract.View> implements SearchContract.Presenter {

    @Override
    protected SearchContract.Module createModule() {
        return new SearchModule();
    }

    @Override
    public void start() {

    }

    @Override
    public void search() {

        if (isViewAttached()) {
            getView().showLoading();
            getModule().searchData(getView().getPage(), getView().getWord(), new OnResultStringCallBack() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, String response) {
                    if (code == Constants.SUCCESS_CODE) {
                        List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                        if (articleList == null || articleList.size() < 1) {
                            getView().onEmpty(tag);
                        } else {
                            getView().searchData(articleList);
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
