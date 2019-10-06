package com.cham.helx.mvp.di.module;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.cham.helx.di.scope.ActivityScope;
import com.cham.helx.mvp.entity.BannerBean;
import com.cham.helx.mvp.contract.UserContract;
import com.cham.helx.mvp.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Hello World
 * Date: 2019/9/20
 * Author: Cham
 */
@Module
public abstract class UserModule {


    /**
     * 括号里的 必须是 UserContract.Model 实现类
     * */
    @Binds
    abstract UserContract.Model bindUserModel(UserModel model);

    @ActivityScope
    @Provides
    static  List<String> provideUserList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static  List<BannerBean> provideBannerBeanList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static LinearLayoutManager provideLayoutManager(UserContract.View view) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }
}
