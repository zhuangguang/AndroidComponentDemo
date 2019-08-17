package com.example.zhuangguang.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.widget.ActionBar;

/**
 * @author 庄光
 * @time 2019/1/20  22:48
 * @desc ${：所有带actionBar的Activity基类}
 */
public abstract class ActionBarActivity<P extends BasePresenter> extends BaseMvpActivity<P>{
    protected ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = findViewById(R.id.actionbar);
    }

    protected void setTitleText(String title) {
        if (actionBar != null) {
            actionBar.setCenterText(title);
        }
    }

    protected void setTitleText(int title) {
        if (actionBar != null) {
            actionBar.setCenterText(getString(title));
        }
    }

    @Override
    protected boolean isActionBar() {
        return true;
    }
}
