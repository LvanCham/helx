package com.cham.helx.base;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Hello World
 * Date: 2019/8/31
 * Author: Cham
 */
public  abstract  class BasePresenter <M extends IModel, V extends IView> implements IPresenter {

    protected CompositeDisposable mCompositeDisposable;
    protected M mModel;
    protected V mRootView;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     * @param rootView
     */
    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    /**
     * 预留
     * */
    @Override
    public void onStart() {

        Log.e("BasePresenter", "onStart: " );
    }

    @Override
    public void onDestroy() {
        unDispose();//解除订阅
        if (mModel != null)
            mModel.onDestroy();
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeDisposable = null;
    }
    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }
}
