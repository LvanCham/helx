package com.cham.helx.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.cham.helx.di.component.AppComponent;
import com.cham.helx.di.component.DaggerAppComponent;

import rxhttp.wrapper.param.RxHttp;

/**
 * Hello World
 * Date: 2019/8/30
 * Author: Cham
 */
public class BaseApplication extends Application {



    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=  DaggerAppComponent.builder().application(this).build();
        appComponent.InjectApp(this);
       Utils.init(this);

        RxHttp.setDebug(true);
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }

}
