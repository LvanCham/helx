package com.cham.helx.mvvm.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 */
public abstract class BaseRepository  {
    public CompositeDisposable compositeDisposable;

    public BaseRepository(){
        compositeDisposable = new CompositeDisposable();
    }

    void onDestory(){
        if(compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }

    }
}
