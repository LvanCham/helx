package com.cham.helx.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import javax.inject.Inject;

/**
 * Hello World
 * Date: 2019/8/31
 * Author: Cham
 */
public abstract class BaseMVPActivity<P extends IPresenter> extends  BaseActivity {


    @Inject
    public  P mPresenter ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInject();
        onInitMvpData();
    }

   public abstract void onInject();
    public abstract void onInitMvpData();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
