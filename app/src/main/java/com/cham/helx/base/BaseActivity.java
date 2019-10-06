package com.cham.helx.base;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Hello World
 * Date: 2019/8/30
 * Author: Cham
 */
public abstract class BaseActivity extends Activity  implements IActivity{


    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder mUnbinder;
    protected Handler mHandler ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(initView(savedInstanceState));
        mUnbinder = ButterKnife.bind(this);
        mHandler = new Handler(Looper.getMainLooper());
        initData();
    }


    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),1080);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;

        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
