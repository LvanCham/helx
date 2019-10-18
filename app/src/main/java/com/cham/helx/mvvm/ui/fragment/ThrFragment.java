package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.cham.helx.BR;
import com.cham.helx.R;
import com.cham.helx.mvvm.bean.Test;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 */
public class ThrFragment extends SupportFragment {
    protected ViewDataBinding binding;
    public static ThrFragment newInstance() {

        Bundle args = new Bundle();

        ThrFragment fragment = new ThrFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragemtn_thr, container, false);
        initData();
        return binding.getRoot();
    }
    private void initData(){
        Test test = new Test();
        test.setS1("123456");
        binding.setVariable(BR.test,test);
        binding.executePendingBindings();





    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(binding!=null){
            binding=null;
        }
    }
}
