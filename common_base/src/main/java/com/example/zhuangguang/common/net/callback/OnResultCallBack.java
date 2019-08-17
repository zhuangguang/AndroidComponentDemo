package com.example.zhuangguang.common.net.callback;

import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxGenericsCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.ResponseBody;

/**
 * @author 庄光
 * @time 2018/12/22  17:38
 * @desc ${网络返回基类}
 */
public abstract class  OnResultCallBack<T>  extends RxGenericsCallback<T, ResponseBody>{
    protected boolean success;

    @Override
    public void onError(Object tag, Throwable e) {
        e.printStackTrace();
        onFailure(tag,e);
    }

    @Override
    public void onCancel(Object tag, Throwable e) {

    }

    @Override
    public void onFailure(Call call, IOException e) {
        onFailure(tag, e);
    }

    @Override
    public void onCompleted(Object tag) {
        super.onCompleted(tag);
        onCompleted();
    }



    @Override
    public void onNext(Object tag, int code, String message, T response) {
        onSuccess(success, code, msg, tag, response);
    }

    protected abstract void onSuccess(boolean success, int code, String msg, Object tag, T response);
    public abstract void onFailure(Object tag, Exception e);
    protected abstract void onCompleted();
}
