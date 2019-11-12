package com.cham.helx.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStore;
import androidx.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.cham.helx.aop.Testaop;
import com.cham.helx.di.component.AppComponent;
import com.cham.helx.di.component.DaggerAppComponent;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor;
import com.squareup.leakcanary.LeakCanary;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;

import java.util.List;

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


    private String TAG ="BaseApplication";

    private ViewModelStore viewModelStore ;

    private long startWorkTimeMillis =0;
    private Handler handler = new Handler(Looper.getMainLooper());
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        //防止多次启动Application
        if (!getPackageName().equals(
                getProcessName(getApplicationContext(), android.os.Process.myPid()))) {
            return;
        }
        initXlog();
        appComponent=  DaggerAppComponent.builder().application(this).build();
        appComponent.InjectApp(this);
        Utils.init(this);
        RxHttp.setDebug(true);
        initFagmentationx();
        HandlerARN();
        mRegisterActivityLifecycleCallbacks();
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
    private void initXlog(){
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                        : LogLevel.NONE)
                .tag("XLoG")                                              // 指定 TAG，默认为 "X-LOG"
                .t()                                                   // 允许打印线程信息，默认禁止
                .st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
                .b()                                                   // 允许打印日志边框，默认禁止
                .addInterceptor(new BlacklistTagsFilterInterceptor(    // 添加黑名单 TAG 过滤器
                        "blacklist1", "blacklist2", "blacklist3"))
                .build();

        XLog.init(LogLevel.ALL,config);

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
    private void HandlerARN(){
        Looper.getMainLooper().setMessageLogging(new Printer() {
            @Override
            public void println(String x) {
                if(x.startsWith(">>>>> Dispatching to Handler")){
                    startWorkTimeMillis = System.currentTimeMillis();
                }else  if(x.startsWith("<<<<< Finished to Handler")){

                    long duration = System.currentTimeMillis() - startWorkTimeMillis;
                    if(duration >100){
                        Log.e(TAG, "主线程执行耗时过长: " + duration +" 毫秒 ，"+ x );
                    }
                }
            }
        });


        handler.post(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        if (e.getMessage()!=null && e.getMessage().startsWith("Unable to start activity")) {
                            android.os.Process.killProcess(android.os.Process.myPid());
                            break;
                        }
                        e.printStackTrace();
                    }

                }
            }

        });

    }
    private void  mRegisterActivityLifecycleCallbacks(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                XLog.i(activity.getLocalClassName());

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(handler!=null){
            handler.removeCallbacksAndMessages(this);
        }
    }
    @Nullable
    public String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }

        return null;
    }
}
