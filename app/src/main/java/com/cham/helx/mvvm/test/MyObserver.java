package com.cham.helx.mvvm.test;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.elvishew.xlog.XLog;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 * 自己实现LifecycleObserver（观察者）
 */
public class MyObserver implements LifecycleObserver  {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){

        XLog.d("onCreate");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        XLog.d("onStart");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        XLog.d("onResume");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        XLog.d("onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(){
        XLog.d("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory(){
        XLog.d("onDestory");
   }


}
