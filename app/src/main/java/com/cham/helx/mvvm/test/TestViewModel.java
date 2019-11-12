package com.cham.helx.mvvm.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.cham.helx.mvvm.base.BaseViewModel;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 */
public class TestViewModel extends BaseViewModel<TestRepository> {
    private MutableLiveData<String> s;
    public TestViewModel(@NonNull Application application) {
        super(application);
        s =new MutableLiveData<>();
    }


    public  void setData(){
        s.postValue(repository.getData());
    }


    public  MutableLiveData<String> getData(){
        return s;
    }

    @Override
    protected TestRepository createRepository() {
        return new TestRepository();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
