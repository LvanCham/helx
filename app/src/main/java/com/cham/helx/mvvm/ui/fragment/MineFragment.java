package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.databinding.FragmentMineBinding;
import com.cham.helx.mvvm.base.BaseMvvmFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

/**
 * Hello World
 * Date: 2019/10/28
 * Author: Cham
 */
public class MineFragment  extends BaseMvvmFragment<FragmentMineBinding> {

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int onLayout() {
        BarUtils.setStatusBarColor(getActivity(), getResources().getColor(R.color.transparent));
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {

        binding.nestedview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
    }
}
