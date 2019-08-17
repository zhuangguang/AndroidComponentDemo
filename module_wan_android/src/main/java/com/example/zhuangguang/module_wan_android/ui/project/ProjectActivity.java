package com.example.zhuangguang.module_wan_android.ui.project;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zhuangguang.common.base.RefreshListActivity;
import com.example.zhuangguang.common.utils.ActivityToActivity;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.module_wan_android.R;
import com.example.zhuangguang.module_wan_android.bean.Article;
import com.example.zhuangguang.module_wan_android.bean.Classification;
import com.example.zhuangguang.module_wan_android.ui.project.adapter.ProjectListAdapter;
import com.example.zhuangguang.module_wan_android.ui.project.mvp.ProjectPresenter;
import com.example.zhuangguang.module_wan_android.ui.project.mvp.contract.ProjectContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  16:36
 * @desc ${TODD}
 */
public class ProjectActivity extends RefreshListActivity<ProjectPresenter> implements ProjectContract.View,
        TypePopupWindow.OnProjectTypeClickListener {
    private List<Classification> types = new ArrayList<>();
    private List<Article> projects = new ArrayList<>();

    private Classification currentClassification;//当前显示的项目分类
    private int page = 1;

    @Override
    protected void initView() {
        super.initView();
        actionBar.setRightText("项目", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TypePopupWindow(mContext)
                        .setData(types)
                        .setCurrent(currentClassification)
                        .setListener(ProjectActivity.this)
                        .show(v);
            }
        });
        presenter.getProjectType();
    }
    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new ProjectListAdapter(mContext, projects, R.layout.wan_item_of_project_list, this);
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public void onItemClick(View view, int position) {
        ActivityToActivity.toWebView(mContext, projects.get(position).getLink());
    }

    @Override
    public void onRefresh() {
        page = 1;
        projects.clear();
        presenter.getProject();
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getProject();
    }

    @Override
    public void onProjectTypeClick(Classification classification) {
        ActivityToActivity.toWebView(mContext, projects.get(position).getLink());
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getTypeId() {
        return currentClassification.getId();
    }

    @Override
    public void projectTypeList(List<Classification> types) {
        this.types.addAll(types);
        currentClassification = types.get(0);
        if (currentClassification != null) {
            setTitleText(currentClassification.getName());
            onRefresh();
        } else {
            showEmptyView("暂无项目数据");
        }
    }

    @Override
    public void projectList(List<Article> projects) {
        this.projects.addAll(projects);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProjectError(String message) {
        if (page == 1) {
            showEmptyView(message);
        } else {
            ToastUtils.showToast(mContext, message);
        }
    }

    @Override
    public void onProjectEmpty() {
        if (page == 1) {
            showEmptyView("暂无项目数据");
        } else {
            ToastUtils.showToast(mContext, "暂无更多项目数据");
        }
    }

    @Override
    public void onEmpty(Object tag) {
        super.onEmpty(tag);
        showEmptyView("暂无项目");

    }

    @Override
    public void onError(Object tag, String errorMsg) {
        super.onError(tag, errorMsg);
        showEmptyView(errorMsg);
    }
}
