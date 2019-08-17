package com.example.zhuangguang.common.base;

import android.support.annotation.CallSuper;
import android.support.v4.view.ViewPager;

import com.example.zhuangguang.common.R;
import com.example.zhuangguang.common.R2;
import com.example.zhuangguang.common.adapter.FragmentPagerAdapter;
import com.example.zhuangguang.common.base.mvp.BasePresenter;
import com.example.zhuangguang.common.bean.HorizontalTabTitle;
import com.example.zhuangguang.common.widget.PagerSlidingTabStrip;

import java.util.List;

import butterknife.BindView;

/**
 * @author 庄光
 * @time 2019/1/20  22:54
 * @desc ${带水平选项卡的Activity}
 */
public abstract class HorizontalTabActivity<P extends BasePresenter> extends ActionBarActivity<P> {
    @BindView(R2.id.pst_tab)
    PagerSlidingTabStrip tabStrip;

    @BindView(R2.id.vp_list)
    ViewPager viewPager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_horizontal_tab;
    }
    @CallSuper
    @Override
    protected void initView() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), getTabTitles()) {
            @Override
            public BaseFragment getTabFragment() {
                return HorizontalTabActivity.this.getTabFragment();
            }
        });
        tabStrip.setViewPager(viewPager);
    }

    protected abstract List<HorizontalTabTitle> getTabTitles();

    protected abstract BaseFragment getTabFragment();
}
