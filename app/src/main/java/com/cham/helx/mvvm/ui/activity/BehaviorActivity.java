package com.cham.helx.mvvm.ui.activity;

import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.databinding.FragmentBehaviorBinding;
import com.cham.helx.mvvm.base.BaseMvvmActivity;

/**
 * Hello World
 * Date: 2019/10/29
 * Author: Cham
 */
public class BehaviorActivity extends BaseMvvmActivity<FragmentBehaviorBinding> {
    @Override
    public int onLayout() {
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.transparent));
        return R.layout.fragment_behavior;
    }

    @Override
    public void initView() {



    }
}
