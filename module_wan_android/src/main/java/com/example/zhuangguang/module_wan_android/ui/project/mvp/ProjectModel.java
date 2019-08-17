package com.example.zhuangguang.module_wan_android.ui.project.mvp;

import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.net.HttpUtils;
import com.example.zhuangguang.common.net.callback.OnResultCallBack;
import com.example.zhuangguang.module_wan_android.ui.project.mvp.contract.ProjectContract;

/**
 * @author 庄光
 * @time 2019/8/4  16:39
 * @desc ${TODD}
 */
public class ProjectModel implements ProjectContract.Model {


    @Override
    public void getProjectType(OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(Api.PROJECT, callback);
    }

    @Override
    public void getProject(int page, int id, OnResultCallBack callback) {
        HttpUtils.getInstance()
                .getRequest(String.format(Api.PROJECT_LIST, page, id), callback);
    }
}
