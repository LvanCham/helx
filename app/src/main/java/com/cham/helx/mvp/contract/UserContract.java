package com.cham.helx.mvp.contract;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.cham.helx.base.IModel;
import com.cham.helx.base.IView;
import com.cham.helx.mvp.entity.PoetryEntity;

import io.reactivex.Observable;

/**
 * Hello World
 * Date: 2019/9/21
 * Author: Cham
 */
public interface UserContract {

    interface  View extends IView{
       Activity getActivity();
        void ShowMsg(String s);
    }

    interface  Model extends IModel{
       Observable<PoetryEntity> getPoerty();
    }
}
