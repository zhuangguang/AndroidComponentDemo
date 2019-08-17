package com.example.zhuangguang.module.main.ui.main;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.example.zhuangguang.common.base.ActionBarActivity;
import com.example.zhuangguang.common.base.BaseApplication;
import com.example.zhuangguang.common.base.BaseFragment;
import com.example.zhuangguang.common.bean.AppInfo;
import com.example.zhuangguang.common.bean.Template;
import com.example.zhuangguang.common.constants.ARouterConfig;
import com.example.zhuangguang.common.manage.UpdateManager;
import com.example.zhuangguang.common.net.Api;
import com.example.zhuangguang.common.utils.ARouterUtils;
import com.example.zhuangguang.common.utils.PermissionsUtils;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.common.widget.dialog.AppDialog;
import com.example.zhuangguang.module.main.R;
import com.example.zhuangguang.module.main.R2;
import com.example.zhuangguang.module.main.ui.main.fragment.CenterFragment;
import com.example.zhuangguang.module.main.ui.main.mvp.MainPresenter;
import com.example.zhuangguang.module.main.ui.main.mvp.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 庄光
 * @time 2019/1/30  22:15
 * @desc ${程序入口}
 */
public class MainActivity extends ActionBarActivity<MainPresenter> implements MainContract.View{

    @BindView(R2.id.rg_main)
    RadioGroup mainTab;

    private long mExitTime;
    /**
     * 存放切换Fragment
     */
    private List<Fragment> mFragmentList = new ArrayList<>();

    //玩android模块Fragment
    private BaseFragment wanFragment = ARouterUtils.getFragment(ARouterConfig.WAN_MAIN_FRAGMENT);
    //我的模块Fragment
    private BaseFragment userFragment = ARouterUtils.getFragment(ARouterConfig.USER_MAIN_FRAGMENT);
    //中间FragmentData
    private ArrayList<Template> centerFragmentData = new ArrayList<>();
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initView() {
        actionBar.showBackImg(false);
        changeFragment(ARouterConfig.WAN_MAIN_FRAGMENT);
        setTitleText(R.string.main_tab_home);
        mainTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rb_main) {
                    changeFragment(ARouterConfig.WAN_MAIN_FRAGMENT);
                    setTitleText(R.string.main_tab_home);
                } else if (checkedId == R.id.rb_center) {
                    changeFragment(CenterFragment.class.getName());
                    setTitleText(R.string.main_tab_center);
                } else if (checkedId == R.id.rb_user) {
                    changeFragment(ARouterConfig.USER_MAIN_FRAGMENT);
                    setTitleText(R.string.main_tab_user);
                } else {
                    changeFragment(ARouterConfig.WAN_MAIN_FRAGMENT);
                    setTitleText(R.string.main_tab_home);
                }
            }
        });

        if(PermissionsUtils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            presenter.checkUpdate();
        }
        presenter.getTabList();

    }

    public void changeFragment(String tag){
        hideFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if(fragment != null) {
            transaction.show(fragment);
        }else {
            if (TextUtils.equals(tag, ARouterConfig.WAN_MAIN_FRAGMENT)) {
                fragment = wanFragment;
            } else if (TextUtils.equals(tag, CenterFragment.class.getName())) {
                fragment = CenterFragment.getInstance(centerFragmentData);
            } else if (TextUtils.equals(tag, ARouterConfig.USER_MAIN_FRAGMENT)) {
                fragment = userFragment;
            } else {
                fragment = wanFragment;
            }
            mFragmentList.add(fragment);
            transaction.add(R.id.fl_context, fragment, tag);
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment f : mFragmentList) {
            transaction.hide(f);
        }
        transaction.commit();
    }

    @Override
    public void needUpdate(AppInfo appInfo) {
        String context = "版本更新！";
        if(appInfo !=null && !TextUtils.isEmpty(appInfo.getDescribe())) {
            context = appInfo.getDescribe();
        }
        new AppDialog(mContext)
                .setTitle("提示更新")
                .setContent(context)
                .setRightButton("更新", new AppDialog.OnButtonClickListener() {
                    @Override
                    public void onClick(String val) {
                        UpdateManager.getInstance(mContext).download(Api.DOWNLOAD_APK);
                    }
                })
                .show();

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showToast(mContext, getString(R.string.main_exit_app));
                mExitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getApplication().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void tabList(List<Template> blockList) {
        this.centerFragmentData.addAll(blockList);
    }
}
