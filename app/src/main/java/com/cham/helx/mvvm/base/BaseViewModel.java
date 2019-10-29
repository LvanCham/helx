package com.cham.helx.mvvm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 */
public abstract  class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {
    protected T repository;
    public BaseViewModel(@NonNull Application application)   {
        super(application);
        this.repository = createRepository();
    }
    protected abstract T createRepository();

    public T getRepository() {
        return repository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestory();
    }
}
