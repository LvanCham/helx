package com.cham.helx.mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/19
 * Author: Cham
 */
public abstract class BaseMvvmFragment<T extends ViewDataBinding> extends SupportFragment {

    public  T binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, onLayout(),container,false);
        binding.setLifecycleOwner(this);
        initView();
        return binding.getRoot();
    }
    public abstract int onLayout();

    public abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(binding!=null){
            binding.unbind();
        }
    }
}
