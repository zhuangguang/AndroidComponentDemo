package com.example.zhuangguang.common.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.zhuangguang.common.base.BaseActivity;
import com.example.zhuangguang.common.base.BaseFragment;

/**
 * @author 庄光
 * @time 2019/2/13  23:10
 * @desc ${ARouter帮助类}
 */
public class ARouterUtils {

    public static BaseFragment getFragment(String path){
        return (BaseFragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    public static BaseActivity getActivity(String path){
        return (BaseActivity)ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
