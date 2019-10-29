package com.cham.helx.mvvm.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.cham.helx.R;
import com.cham.helx.databinding.FragmentTwoBinding;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.madapter.TietleNavigatorAdapter;
import com.cham.helx.madapter.Viewpager2Adapter;
import com.cham.helx.mvvm.base.BaseMvvmFragment;
import com.cham.helx.mvvm.test.Test;
import com.cham.helx.mvvm.test.TestRcyAdapter;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;
import com.elvishew.xlog.XLog;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 * ViewDataBinding的用法
 *
 */
public class TwoFragment extends BaseMvvmFragment<FragmentTwoBinding> implements
        Viewpager2Adapter.converHolder
,TestRcyAdapter.OnRcyAdapterclick{

    public static TwoFragment newInstance() {
        Bundle args = new Bundle();
        TwoFragment fragment = new TwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private List<Test> list;
    private TestRcyAdapter testRcyAdapter;


    private List<String> mAllData;


    private TietleNavigatorAdapter tietleNavigatorAdapter ;

    //导航栏
    private CommonNavigator commonNavigator;


    private Viewpager2Adapter viewpager2Adapter ;
    @Override
    public int onLayout() {
        return R.layout.fragment_two;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            switch (i){
                case 0:
                    Test test  =new Test();
                    test.setS1("人教版");
                    List<String> mTitles = new ArrayList<>();
                    mTitles.add("一年级");
                    test.setmTlist(mTitles);
                    list.add(test);
                    break;
                case 1:
                    Test test1  =new Test();
                    test1.setS1("苏教版");
                    List<String> mTitles1 = new ArrayList<>();
                    mTitles1.add("一年级");
                    mTitles1.add("二年级");
                    test1.setmTlist(mTitles1);
                    list.add(test1);
                    break;
                case 2:
                    Test test2  =new Test();
                    test2.setS1("太难教版");
                    List<String> mTitles2 = new ArrayList<>();
                    mTitles2.add("一年级");
                    mTitles2.add("二年级");
                    mTitles2.add("三年级");
                    mTitles2.add("四年级");
                    test2.setmTlist(mTitles2);
                    list.add(test2);
                    break;
                case 3:
                    Test test3  =new Test();
                    test3.setS1("真难教版");
                    List<String> mTitles3 = new ArrayList<>();
                    mTitles3.add("一年级");
                    mTitles3.add("二年级");
                    mTitles3.add("三年级");
                    mTitles3.add("四年级");
                    mTitles3.add("五年级");
                    test3.setmTlist(mTitles3);
                    list.add(test3);
                    break;
                case 4:
                    Test test4  =new Test();
                    test4.setS1("绝对人教版");
                    List<String> mTitles4 = new ArrayList<>();
                    mTitles4.add("一年级");
                    mTitles4.add("二年级");
                    mTitles4.add("三年级");
                    mTitles4.add("四年级");
                    test4.setmTlist(mTitles4);
                    list.add(test4);
                    break;
                case 5:
                    Test test5  =new Test();
                    test5.setS1("没人教版");
                    List<String> mTitles5 = new ArrayList<>();
                    mTitles5.add("一年级");
                    mTitles5.add("二年级");
                    mTitles5.add("三年级");
                    mTitles5.add("四年级");
                    mTitles5.add("五年级");
                    test5.setmTlist(mTitles5);
                    list.add(test5);
                    break;
                case 6:
                    Test test6  =new Test();
                    test6.setS1("天人教版");
                    List<String> mTitles6 = new ArrayList<>();
                    mTitles6.add("一年级");
                    mTitles6.add("二年级");
                    mTitles6.add("三年级");
                    mTitles6.add("四年级");
                    mTitles6.add("五年级");
                    mTitles6.add("六年级");
                    test6.setmTlist(mTitles6);
                    list.add(test6);
                    break;
                case 7:
                    Test test7  =new Test();
                    test7.setS1("爱人教版");
                    List<String> mTitles7 = new ArrayList<>();
                    mTitles7.add("一年级");
                    mTitles7.add("二年级");
                    mTitles7.add("三年级");
                    mTitles7.add("四年级");
                    mTitles7.add("五年级");
                    mTitles7.add("六年级");
                    mTitles7.add("七年级");
                    test7.setmTlist(mTitles7);
                    list.add(test7);
                    break;
                case 8:
                    Test test8  =new Test();
                    test8.setS1("做人教版");
                    List<String> mTitles8 = new ArrayList<>();
                    mTitles8.add("一年级");
                    mTitles8.add("二年级");
                    mTitles8.add("三年级");
                    mTitles8.add("四年级");
                    mTitles8.add("五年级");
                    mTitles8.add("六年级");
                    mTitles8.add("七年级");
                    mTitles8.add("八年级");
                    test8.setmTlist(mTitles8);
                    list.add(test8);
                    break;
                case 9:
                    Test test9  =new Test();
                    test9.setS1("叫人教版");
                    List<String> mTitles9 = new ArrayList<>();
                    mTitles9.add("一年级");
                    mTitles9.add("二年级");
                    mTitles9.add("三年级");
                    mTitles9.add("四年级");
                    mTitles9.add("五年级");
                    mTitles9.add("六年级");
                    mTitles9.add("七年级");
                    mTitles9.add("八年级");
                    mTitles9.add("九年级");
                   test9.setmTlist(mTitles9);
                    list.add(test9);
                    break;
            }
        }
        testRcyAdapter = new TestRcyAdapter(getActivity(),list);
        testRcyAdapter.setOnClick(this);
        binding.rcySc.setAdapter(testRcyAdapter);
        binding.rcySc.setHasFixedSize(true);

        //全部课程
        mAllData = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            mAllData.add("");
        }
        tietleNavigatorAdapter = new TietleNavigatorAdapter();
        tietleNavigatorAdapter.setList(list.get(0).getmTlist());
        tietleNavigatorAdapter.setViewpager( binding.vpg2Sc);

         commonNavigator = new CommonNavigator(getActivity());
        //ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(tietleNavigatorAdapter);

        binding.magivSc.setNavigator(commonNavigator);
        viewpager2Adapter = new Viewpager2Adapter(getActivity(),list.get(0).getmTlist(),R.layout.item_recyclerview);
        viewpager2Adapter.setcConverHolder(this);
        binding.vpg2Sc.setAdapter(viewpager2Adapter);


        binding.vpg2Sc.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.magivSc.onPageSelected(position);
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                binding.magivSc.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                binding.magivSc.onPageScrollStateChanged(state);
            }
        });
    }


    @Override
    public void convert(BaseViewHolder holder, int position) {
        RecyclerView mRcyItem = holder.getView(R.id.item_recyclerview_1);
        mRcyItem.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item_rcy_1, mAllData) {
            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });
        mRcyItem.setHasFixedSize(true);
    }


    @Override
    public void itemOnclick(int position, List<Test> datas) {
           tietleNavigatorAdapter.updateData(datas.get(position).getmTlist());
           commonNavigator.notifyDataSetChanged();
          viewpager2Adapter.updateData(datas.get(position).getmTlist());

    }
}
