package com.cham.helx.mvvm;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.mvvm.ui.fragment.OneFragment;
import com.cham.helx.mvvm.ui.fragment.TestFragment;
import com.cham.helx.mvvm.ui.fragment.ThrFragment;
import com.cham.helx.mvvm.ui.fragment.TwoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 */
public class MainAty extends SupportActivity  {
    private String TAG = "MainAty";

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.bottom_navi)
    BottomNavigationView bottomNavi;

    private  SupportFragment []mFragments = new  SupportFragment[3] ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        //黑色字体
        BarUtils.setStatusBarLightMode(this, true);
        //透明状态栏
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.white));
         initView();
    }

    private void initView(){
        bottomNavi.setItemIconTintList(null);
        if (findFragment(OneFragment.class) == null) {
            mFragments[0] = OneFragment.newInstance();
            mFragments[1] = TwoFragment.newInstance();
            mFragments[2] = TestFragment.newInstance();

           loadMultipleRootFragment(R.id.fl_content, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]);
        } else {
            mFragments[0] = findFragment(OneFragment.class);
            mFragments[1] = findFragment(TwoFragment.class);
            mFragments[2] = findFragment(TestFragment.class);
        }
        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = item.getTitle().toString();
                Log.e(TAG, "onNavigationItemSelected: "+ title );
                switch (item.getItemId()) {
                    case R.id.menu_one:
                        showHideFragment(mFragments[0]);
                        break;
                    case R.id.menu_two:
                        showHideFragment(mFragments[1]);
                        break;
                    case R.id.menu_thr:
                        showHideFragment(mFragments[2]);
                        break;
                }
                return true;
            }
        });


    }


}
