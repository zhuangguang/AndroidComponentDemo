package com.example.zhuangguang.module_user.ui.about;

import android.widget.TextView;

import com.example.zhuangguang.common.base.ActionBarActivity;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.utils.Utils;
import com.example.zhuangguang.module_user.R;
import com.example.zhuangguang.module_user.R2;

import butterknife.BindView;

/**
 * @author 庄光
 * @time 2019/7/27  17:50
 * @desc ${TODD}
 */
public class AboutActivity extends ActionBarActivity {
    @BindView(R2.id.tv_version)
    TextView mTvVersion;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_activity_about;
    }

    @Override
    protected void initView() {
        setTitleText("关于");
        mTvVersion.setText(String.format("当前版本：%s", Utils.getVersionName()));
    }


}
