package com.cham.helx.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.cham.helx.base.BasePresenter;
import com.cham.helx.di.scope.ActivityScope;
import com.cham.helx.mvp.contract.UserContract;
import com.rxjava.rxlife.RxLife;


import javax.inject.Inject;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

/**
 * Hello World
 * Date: 2019/9/21
 * Author: Cham
 */
@ActivityScope
public class UserPresenter  extends  BasePresenter<UserContract.Model, UserContract.View>{



    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View rootView){
        super(model,rootView);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e("UserPresenter", "onStart: " );
    }




    public  void dosth(){
        //加载数据什么的


        mModel.getPoerty()
                . subscribeOn(io())
                .observeOn(mainThread())
                .subscribe(poetryEntity -> {
                    mRootView.ShowMsg(poetryEntity.getContent());
                },throwable -> {

                });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
