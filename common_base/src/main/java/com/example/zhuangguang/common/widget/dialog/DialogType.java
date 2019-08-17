package com.example.zhuangguang.common.widget.dialog;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 庄光
 * @time 2019/2/12  22:31
 * @desc ${对话框类型}
 */
public interface DialogType {
    @IntDef({DEFAULT, INPUT, COUNT, NO_TITLE, BOTTOM_IN})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    int DEFAULT = 0;
    int INPUT = 1;
    int COUNT = 2;
    int NO_TITLE = 3;
    int BOTTOM_IN = 4;
}

