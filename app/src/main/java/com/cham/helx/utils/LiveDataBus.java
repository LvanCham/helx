package com.cham.helx.utils;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello World
 * Date: 2019/10/23
 * Author: Cham
 * LiveData实现事件总线
 */
public class LiveDataBus {

    private  final Map<String , MutableLiveData<Object>> bus ;

    private LiveDataBus(){
        bus = new HashMap<>();
    }

    private  static  class  SingletonHolder{
        private static  final LiveDataBus LIVE_DATA_BUS= new LiveDataBus();
    }
    public static  LiveDataBus get(){
        return SingletonHolder.LIVE_DATA_BUS;
    }

    public  synchronized  <T>MutableLiveData<T> with(String key ,Class<T> type){
        if (!bus.containsKey(key)){
            bus.put(key,new BusMutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }

}
