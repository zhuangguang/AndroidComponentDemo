package com.example.zhuangguang.common.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author 庄光
 * @time 2019/2/13  22:19
 * @desc ${键盘辅助类}
 */
public class KeyboardUtils {

    public static void showKeyboard(View view){
        InputMethodManager inm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inm != null) {
            view.requestFocus();
            inm.showSoftInput(view,0);
        }
    }

    public static void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromInputMethod(view.getWindowToken(),0);
        }
    }

    public static void toggleSoftInput(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.toggleSoftInput(0,0);
        }
    }
}
