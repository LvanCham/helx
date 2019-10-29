package com.cham.helx.mvvm.test;

import android.util.Log;

import com.cham.helx.mvp.entity.PoetryEntity;
import com.cham.helx.mvvm.base.BaseRepository;
import com.rxjava.rxlife.RxLife;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import rxhttp.wrapper.param.RxHttp;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 */
public class TestRepository extends BaseRepository {

    private String a;
    /**
     * 请求数据
     * */
    public  String getData(){
        RxHttp.get("/all.json")
                .asObject(PoetryEntity.class)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                 .subscribeOn(mainThread())
                .observeOn(io())
                .subscribe(s ->{
                    a =  s.getContent();
                },throwable -> {

                });

        return  a;
    }


}
