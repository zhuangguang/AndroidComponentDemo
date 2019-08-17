package com.example.zhuangguang.module_wan_android.ui.project.mvp;

import com.alibaba.fastjson.JSON;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.constants.Constants;
import com.example.zhuangguang.common.net.callback.OnResultListCallBack;
import com.example.zhuangguang.common.net.callback.OnResultStringCallBack;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.bean.Classification;
import com.example.zhuangguang.module_wan_android.ui.project.mvp.contract.ProjectContract;

import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  16:45
 * @desc ${TODD}
 */
public class ProjectPresenter extends BasePresenter<ProjectContract.Model, ProjectContract.View>
        implements ProjectContract.Presenter {
        @Override
        protected ProjectContract.Model createModule() {
                return new ProjectModel();
        }

        @Override
        public void start() {
                getProjectType();
        }

        @Override
        public void getProjectType() {
                if (isViewAttached()) {
                        getView().showLoading();
                        getModule().getProjectType(new OnResultListCallBack<List<Classification>>() {
                                @Override
                                public void onSuccess(boolean success, int code, String msg, Object tag, List<Classification> response) {
                                        if (code == Constants.SUCCESS_CODE) {
                                                if (response != null && response.size() > 0) {
                                                        getView().projectTypeList(response);
                                                } else {
                                                        getView().onEmpty(tag);
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
        public void getProject() {
                if (isViewAttached()) {
                        getView().showLoading();
                        getModule().getProject(getView().getPage(), getView().getTypeId(), new OnResultStringCallBack() {
                                @Override
                                public void onSuccess(boolean success, int code, String msg, Object tag, String response) {
                                        if (code == Constants.SUCCESS_CODE) {
                                                final List<Article> articleList = JSON.parseArray(JSON.parseObject(response).getString("datas"), Article.class);
                                                if (articleList == null || articleList.size() < 1) {
                                                        getView().onProjectEmpty();
                                                } else {
                                                        getView().projectList(articleList);
                                                }

                                        } else {
                                                getView().onProjectError(msg);
                                        }
                                }

                                @Override
                                public void onFailure(Object tag, Exception e) {
                                        getView().onProjectError(Constants.ERROR_MESSAGE);
                                }

                                @Override
                                public void onCompleted() {
                                        getView().dismissLoading();
                                }
                        });
                }
        }
}

