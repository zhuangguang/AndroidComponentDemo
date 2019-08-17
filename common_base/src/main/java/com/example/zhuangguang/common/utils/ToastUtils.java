package com.example.zhuangguang.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author 庄光
 * @time 2019/2/13  22:33
 * @desc ${Toast}
 */
public class ToastUtils {

    public static void showToast(Context context, int strings){
        showToast(context,context.getString(strings));
    }

    public static void showToast(Context context, String text) {
        //        showToast(context, title, Gravity.BOTTOM);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
