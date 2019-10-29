package com.cham.helx.mvvm.base;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.AdaptScreenUtils;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Hello World
 * Date: 2019/10/22
 * Author: Cham
 */
public abstract class BaseMvvmActivity<T extends ViewDataBinding>  extends SupportActivity {

    public T binding ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,onLayout());
        binding.setLifecycleOwner(this);
        initView();
    }

    public abstract int onLayout();

    public abstract void initView();

    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptWidth(super.getResources(),1080);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binding!=null){
            binding.unbind();
        }
    }
}
