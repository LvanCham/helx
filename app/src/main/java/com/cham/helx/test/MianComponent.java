package com.cham.helx.test;

import com.cham.helx.MainActivity;
import com.cham.helx.di.scope.ActivityScope;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Hello World
 * Date: 2019/9/12
 * Author: Cham
 */

@ActivityScope
@Component(modules = MainModule.class)
public interface MianComponent {


   StuC getStuC();


   void InjectAty(MainActivity activity);

   @Component.Builder
   interface  Builder{
      @BindsInstance
      //提供参数的
      MianComponent.Builder b(String s );
      MianComponent build();
   }
}
