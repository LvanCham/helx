package com.cham.helx.mvp.di.component;

import com.cham.helx.di.component.AppComponent;
import com.cham.helx.di.scope.ActivityScope;
import com.cham.helx.mvp.contract.UserContract;
import com.cham.helx.mvp.di.module.UserModule;
import com.cham.helx.mvp.ui.UserMvpActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Hello World
 * Date: 2019/9/20
 * Author: Cham
 */
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {


    void InjectAty(UserMvpActivity userMvpActivity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        UserComponent.Builder view(UserContract.View view);
        UserComponent.Builder appComponent(AppComponent appComponent);
        UserComponent build();
    }


}
