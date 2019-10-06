package com.cham.helx.di.module;



import android.app.Application;


import androidx.fragment.app.FragmentManager;

import com.cham.helx.utils.DataHelper;
import com.cham.helx.utils.FragmentLifecycle;

import com.cham.helx.utils.SpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;


import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;



/**
 * Hello World
 * Date: 2019/8/31
 * Author: Cham
 * MyAppModule
 * 比如打印日志
 */
@Module
public abstract class AppModule {

    @Binds
    abstract FragmentManager.FragmentLifecycleCallbacks bindFragmentLifecycle(FragmentLifecycle fragmentLifecycle);

    @Singleton
    @Provides
    static List<FragmentManager.FragmentLifecycleCallbacks> provideFragmentLifecycles() {
        return new ArrayList<>();
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder
                .serializeNulls()//支持序列化值为 null 的参数
                .enableComplexMapKeySerialization();//支持将序列化 key 为 Object 的 Map, 默认只能序列化 key 为 String 的 Map
        return builder.create();
    }


    @Singleton
    @Provides
    static SpUtils provideSp(Application application){
        return  SpUtils.getInstance(application);
    }



}
