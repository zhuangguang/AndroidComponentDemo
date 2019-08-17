package com.example.zhuangguang.module_wan_android.ui.wxnumber;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhuangguang.common.base.BaseFragment;
import com.example.zhuangguang.common.base.HorizontalTabActivity;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.module_wan_android.bean.WXNumber;
import com.example.zhuangguang.module_wan_android.ui.wxnumber.fragment.WXArticleFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 庄光
 * @time 2019/8/4  17:11
 * @desc ${TODD}
 */
public class WXNumberActivity extends  HorizontalTabActivity {

    private ArrayList<WXNumber> wxNumbers = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null) {
            finish();
        }
        setTitleText("公众号");
        wxNumbers.addAll((Collection<? extends WXNumber>) getIntent().getSerializableExtra("WXNumbers"));
    }

    @Override
    protected List<HorizontalTabTitle> getTabTitles() {
        if (wxNumbers.size() < 1) {
            finish();
        }
        List<HorizontalTabTitle> tabTitleList = new ArrayList<>();
        for (WXNumber wx : wxNumbers) {
            tabTitleList.add(new HorizontalTabTitle<>(wx.getName(), wx));
        }
        return tabTitleList;
    }

    @Override
    protected BaseFragment getTabFragment() {
        return new WXArticleFragment();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
