package com.example.zhuangguang.common.widget.pulltorefresh;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 庄光
 * @time 2019/1/28  20:47
 * @desc ${刷新状态}
 */
interface State {
    @IntDef({REFRESH, LOAD_MORE})
    @Retention(RetentionPolicy.SOURCE)
    @interface REFRESH_STATE{

    }

    int REFRESH = 10;
    int LOAD_MORE = 11;
}
