package com.cham.helx.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * Hello World
 * Date: 2019/10/23
 * Author: Cham
 * class: hook 使observer.mLastVersion = mVersion; 就不会走 回调OnChange方法
 */
public class BusMutableLiveData<T> extends MutableLiveData<T> {


    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, observer);
        try {
            hook((Observer<T>) observer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 反射技术
     * 使 observer..mLastVersion =mVersion
     * */

    private  void  hook(Observer<T> observer) throws Exception  {

        Class<LiveData> liveDataClass  = LiveData.class;
        //通过反射获取该类里的mObserver 属性对象
        Field fieldObserver  =liveDataClass.getDeclaredField("mObservers");
        //设置属性可以被访问
        fieldObserver.setAccessible(true);
        //获取的对象是 this 里这个对象值 ，他的值 是一个map集合
        Object objectObservers = fieldObserver.get(this);
        //获取map对象的类型
        Class<?> classObservers =objectObservers.getClass();
        //获取map对象中所有的get方法
        Method methodObservers =classObservers.getDeclaredMethod("get", Objects.class);
        //设置get方法可以被访问
        methodObservers.setAccessible(true);
        //执行该get方法，传入objectObservers 对象，然后传入observer作为key值
        Object objectWrapperEntry = methodObservers.invoke(objectObservers,observer);
        //定义一个空的object对象
        Object objectWrapper=null;
        //objectWrapperEntry 是否为 Map.Entry 类型
        if(objectWrapperEntry instanceof Map.Entry){
            objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
        }
        if(objectWrapper ==null){
            throw  new NullPointerException("Wrapper can not be null");
        }

        //如果不是空 就的到该object 的父类
        Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
        Field fieldLastVersion =classObserverWrapper.getDeclaredField("mLastVersion");
        fieldLastVersion.setAccessible(true);
        Field fieldVersion = liveDataClass.getDeclaredField("mVersion");
        fieldVersion.setAccessible(true);
        Object objectVersion = fieldVersion.get(this);
        //把mVersion 字段的属性值 设置给mLastVersion
        fieldLastVersion.set(objectWrapper,objectVersion);





    }




}
