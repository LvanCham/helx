package com.cham.helx.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.cham.helx.di.component.AppComponent;
import com.cham.helx.di.component.DaggerAppComponent;

import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
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

        initFagmentationx();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


    private void initFagmentationx(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
