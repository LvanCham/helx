package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
 * ViewDataBinding的用法
 *
 */
public class TwoFragment extends SupportFragment {

    protected ViewDataBinding binding;

    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false);

        initData();
        return binding.getRoot();
    }
    private void initData() {
        Test test = new Test();
        binding.setVariable(com.cham.helx.BR.test, test);
        binding.executePendingBindings();





    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding = null;
        }

    }

}
