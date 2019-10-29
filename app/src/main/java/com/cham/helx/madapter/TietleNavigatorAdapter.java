package com.cham.helx.madapter;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager2.widget.ViewPager2;

import com.cham.helx.R;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.ColorUtils.getColor;

/**
 * Hello World
 * Date: 2019/10/28
 * Author: Cham
 */
public class TietleNavigatorAdapter  extends   CommonNavigatorAdapter {



    private ViewPager2  viewPager2;


    private List<String> list =new ArrayList<>();




    public void setViewpager(ViewPager2  viewPager){
        viewPager2 =viewPager;
    }

    public void setList(List<String> list1){
        list .addAll(list1);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
        simplePagerTitleView.setText(list.get(index));
        simplePagerTitleView.setTextSize(18);
        simplePagerTitleView.getPaint().setFakeBoldText(true);
        simplePagerTitleView.setNormalColor(getColor(R.color.tab_unchecked));
        simplePagerTitleView.setSelectedColor(getColor(R.color.black));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(index);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(UIUtil.dip2px(context, 5));
        indicator.setLineWidth(UIUtil.dip2px(context, 30));
        indicator.setRoundRadius(UIUtil.dip2px(context, 3));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(3.0f));
        indicator.setColors(getColor(R.color.colorAccent));
        return indicator;
    }

    public void updateData(List dataSet) {
        this.list.clear();
        appendData(dataSet);
    }

    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.list.addAll(dataSet);
            notifyDataSetChanged();
        }
    }
}
