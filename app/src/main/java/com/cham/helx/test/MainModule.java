package com.cham.helx.test;


import com.cham.helx.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * Hello World
 * Date: 2019/9/12
 * Author: Cham
 */

@Module
public  abstract class MainModule {
//    private String age;
//    public MainModule(String age) {
//        this.age = age;
//    }
//    //提供参数的提供者
//    @ActivityScope
//    @Provides
//    public String ageProvider() {
//        return age;
//    }
//    //提供对象的提供者
//    @Provides
//    public StuA sellMoeProvider(String age) {
//        return new StuA(age);
//    }


    /**
     * 第一种注入
    * */
    @ActivityScope
    @Binds
    abstract  StuB stuB(StuA stuA);


    /**
     * 第二种
     * */

    //这里的参数是 build 提供
    @ActivityScope//单例  内部双重检查
    @Provides
    static StuC provideStuC() {
        return new StuC();
    }
}
