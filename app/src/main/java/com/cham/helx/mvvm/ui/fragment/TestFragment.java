package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cham.helx.R;
import com.cham.helx.databinding.FragmentTestBinding;
import com.cham.helx.mvvm.base.BaseMvvmFragment;
import com.cham.helx.mvvm.test.Test;
import com.cham.helx.mvvm.test.TestViewModel;


/**
 * Hello World
 * Date: 2019/10/19
 * Author: Cham
 */
public class TestFragment extends BaseMvvmFragment<FragmentTestBinding> {

    private TestViewModel testViewModel;
    private String TAG  ="TestFragment";
    public static TestFragment newInstance() {
        Bundle args = new Bundle();
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int onLayout() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView() {
        Test test = new Test();
        binding.setTest(test);
        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                test.setS1(s);
               binding.setTest(test);
            }
        });


        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testViewModel.setData();
            }
        });
    }
}
