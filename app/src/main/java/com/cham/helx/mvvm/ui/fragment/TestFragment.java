package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;

import com.cham.helx.R;
import com.cham.helx.databinding.FragmentTestBinding;
import com.cham.helx.mvvm.base.BaseMvvmFragment;
import com.cham.helx.mvvm.bean.Test;

/**
 * Hello World
 * Date: 2019/10/19
 * Author: Cham
 */
public class TestFragment extends BaseMvvmFragment<FragmentTestBinding> {


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
        t.setTest(test);
    }
}
