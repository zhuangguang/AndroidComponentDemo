package com.example.zhuangguang.common.base.mvp;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 庄光
 * @time 2019/1/20  10:45
 * @desc ${TODD}
 */
public abstract class BasePresenter<M extends IBaseModel , V extends IBaseView> {

    private V mProxyView;
    private M module;
    private WeakReference<V> weakReference;
    /**
     * 绑定View
     */
    public void attachView(V view){
        weakReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new MvpViewHandler(weakReference.get()));
        if(this.module == null) {
            this.module = createModule();
        }
    }
    /**
     * 解绑View
     */
    public void detachView(){
        this.module = null;
        if(isViewAttached()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected V getView(){
        return mProxyView;
    }

    protected M getModule(){
        return module;
    }

    protected Context getContext() {
        return getView().getContext();
    }

    protected void showLoading() {
        getView().showLoading();
    }

    protected void dismissLoading() {
        getView().dismissLoading();
    }

    /**
     * 通过该方法创建Module
     */
    protected abstract M createModule();
    /**
     * 初始化方法
     */
    public abstract void start();

    private class MvpViewHandler implements InvocationHandler{
        private IBaseView mvpView;
        MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没有被销毁，执行V层的方法
            if(isViewAttached()) {
                return method.invoke(mvpView, args);
            }
            //P层不需要关注V层的返回值
            return null;
        }
    }
    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }
}
