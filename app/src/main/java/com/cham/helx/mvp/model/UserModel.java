package com.cham.helx.mvp.model;

import android.util.Log;

import com.cham.helx.base.BaseModel;
import com.cham.helx.di.scope.ActivityScope;
import com.cham.helx.mvp.contract.UserContract;
import com.cham.helx.mvp.entity.PoetryEntity;
import com.rxjava.rxlife.RxLife;

import javax.inject.Inject;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

/**
 * Hello World
 * Date: 2019/9/21
 * Author: Cham
 */
@ActivityScope
public class UserModel  implements UserContract.Model {

    private String TAG="UserModel";
    @Inject
    public UserModel(){}

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
    }



    @Override
    public Observable<PoetryEntity> getPoerty() {
        return   RxHttp.get("/all.json")
                .asObject(PoetryEntity.class);
    }
}
