package com.example.zhuangguang.module_wan_android.ui.collection.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.ui.collection.mvp.contract.CollectionContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/17  17:21
 * @desc ${TODD}
 */
public class CollectionPresenter extends BasePresenter<CollectionContract.Model, CollectionContract.View>
        implements CollectionContract.Presenter {


    @Override
    public void getCollectionList() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getCollectionList(getView().getPage(), new OnResultStringCallBack() {
                @Override
                public void onSuccess(boolean success, int code, String msg, Object tag, String response) {
                    if (code == Constants.SUCCESS_CODE) {
                        List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                        if (articleList == null || articleList.size() < 1) {
                            getView().onEmpty(tag);
                        } else {
                            getView().collectionList(articleList);
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
    protected CollectionModel createModule() {
        return new CollectionModel();
    }

    @Override
    public void start() {
        getCollectionList();
    }

}
