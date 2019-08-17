package com.example.zhuangguang.module_wan_android.ui.main.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultListCallBack;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.bean.BannerInfo;
import com.example.zhuangguang.module_wan_android.bean.WXNumber;
import com.example.zhuangguang.module_wan_android.ui.main.mvp.contract.HomeContract;
import com.example.zhuangguang.module_wan_android.ui.project.ProjectActivity;
import com.example.zhuangguang.module_wan_android.ui.search.SearchActivity;
import com.example.zhuangguang.module_wan_android.ui.setup.SystemActivity;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.WXNumberActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/2/23  17:11
 * @desc ${TODD}
 */
public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> implements  HomeContract.Presenter{
    @Override
    protected HomeContract.Model createModule() {
        return new HomeModel();
    }

    @Override
    public void start() {
        getBanner();
        getBlockList();
        getBanner();
        getWXNumber();
        getArticleList();
    }

    @Override
    public void getBanner() {
        if(isViewAttached()) {
            getView().showLoading();
            getModule().getBanner(new OnResultListCallBack<List<BannerInfo>>(){
                @Override
                protected void onSuccess(boolean success, int code, String msg, Object tag, List<BannerInfo> response) {
                    if(code == 0) {
                        if(response !=null && response.size() >0) {
                            getView().bannerList(response);
                        }else {
                            getView().onEmpty(tag);
                        }
                    }else {
                        getView().onError(tag , msg);
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {
                    getView().onError(tag, Constants.ERROR_MESSAGE);

                }

                @Override
                protected void onCompleted() {

                }
            });
        }

    }

    @Override
    public void getBlockList() {
        if(isViewAttached()) {
            getView().showLoading();
            List<Template> list = new ArrayList<>();
            list.add(new Template("体系", R.drawable.wan_icon_1, SystemActivity.class));
            list.add(new Template("项目", R.drawable.wan_icon_2, ProjectActivity.class));
            list.add(new Template("公众号", R.drawable.wan_icon_3, WXNumberActivity.class));
            list.add(new Template("搜索", R.drawable.wan_icon_4, SearchActivity.class));

            getView().blockList(list);
        }

    }

    @Override
    public void getWXNumber() {
        if(isViewAttached()) {
            getModule().getWXNumber(new OnResultListCallBack<List<WXNumber>>(){

                @Override
                protected void onSuccess(boolean success, int code, String msg, Object tag, List<WXNumber> response) {
                    if(code == Constants.SUCCESS_CODE && response != null && response.size()>0) {
                        getView().wxNumber(response);
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {

                }

                @Override
                protected void onCompleted() {

                }
            });
        }

    }

    @Override
    public void getArticleList() {
        if(isViewAttached()) {
            getView().showLoading();
            getModule().getArticleList(getView().getPage(), new OnResultStringCallBack() {
                @Override
                protected void onSuccess(boolean success, int code, String msg, Object tag, String response) {
                    if(code == Constants.SUCCESS_CODE) {
                        List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                        if(articleList == null || articleList.size() < 1) {
                            getView().onEmpty(tag);
                        }else {
                            getView().articleList(articleList);
                        }

                    }else {
                        getView().onError(tag,msg);
                    }
                }

                @Override
                public void onFailure(Object tag, Exception e) {
                    getView().onError(tag , Constants.ERROR_MESSAGE);

                }

                @Override
                protected void onCompleted() {
                    getView().dismissLoading();

                }
            });
        }

    }




}
