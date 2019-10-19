package com.cham.helx.madapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * Hello World
 * Date: 2019/10/19
 * Author: Cham
 */
public class Viewpager2FragmentAdapter  extends FragmentStateAdapter {


    private List<Fragment> mFragmentList;


    public Viewpager2FragmentAdapter(@NonNull FragmentActivity fragmentActivity,List<Fragment> fragmentList) {
        super(fragmentActivity);
        mFragmentList =fragmentList;

    }

    public Viewpager2FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public Viewpager2FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
