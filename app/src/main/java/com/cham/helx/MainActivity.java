package com.cham.helx;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.cham.helx.base.BaseApplication;
import com.cham.helx.mvp.entity.PoetryEntity;
import com.cham.helx.mvvm.test.MyObserver;
import com.cham.helx.test.DaggerMianComponent;
import com.cham.helx.test.MianComponent;
import com.cham.helx.test.StuA;
import com.cham.helx.test.StuD;
import com.cham.helx.test.StuF;
import com.elvishew.xlog.XLog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rxjava.rxlife.RxLife;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.param.RxHttp;

/**
 *
 * di 基本用法  关联test文件夹
 *
 * */

public class MainActivity extends AppCompatActivity {



    private String TAG = "MainActivity";

    private StuF stuF;


    @Inject
    StuA stuA;

    private MianComponent mianComponent;

    private StuD stuD;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        getLifecycle().addObserver(new MyObserver());

        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted) { // Always true pre-M
                // I can control the camera now
            } else {
                // Oups permission denied
            }
        });

        stuF = new StuF();

        stuF.setS(new StuF.stuS() {
            @Override
            public void S(String sss) {
                Log.e(TAG, "S: " + sss);
            }
        });
        mianComponent=  DaggerMianComponent.builder().b("sss").build();
        mianComponent.InjectAty(this);
        stuD=  mianComponent.getStuC();


        Log.e(TAG, "stuA: " +stuA.getMsg() );

              Log.e(TAG, "stuC: " +stuD.getMsg("AAA") );

        ((BaseApplication) getApplication()).getAppComponent();


//        RxHttp.get("all.json").execute();

        RxHttp.get("/all.json")
                .asObject(PoetryEntity.class)
                .as(RxLife.asOnMain(this))
                .subscribe(s ->{
                    Log.e(TAG, "onCreate: "+ s.getContent() );
                },throwable -> {

                });


      XLog.e(AdaptScreenUtils.pt2Px(1080));


    }


    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),1080);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Home 電源", "按鈕");
        Log.e(TAG, "onSaveInstanceState: " + outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //当activity重建的时候，会调用onRestoreInstanceState
        Log.e(TAG, "onRestoreInstanceState: " + savedInstanceState);
    }
}
