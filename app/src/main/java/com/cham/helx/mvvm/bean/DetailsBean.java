package com.cham.helx.mvvm.bean;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.cham.helx.R;
import com.cham.helx.mvvm.ui.activity.DetailsActivity;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * Hello World
 * Date: 2019/10/18
 * Author: Cham
 */
public class DetailsBean {

    private boolean isShow;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getIvHome() {
        return ivHome;
    }

    public void setIvHome(int ivHome) {
        this.ivHome = ivHome;
    }

    public int getIvCostomer() {
        return ivCostomer;
    }

    public void setIvCostomer(int ivCostomer) {
        this.ivCostomer = ivCostomer;
    }

    public int getIvShoppingcar() {
        return ivShoppingcar;
    }

    public void setIvShoppingcar(int ivShoppingcar) {
        this.ivShoppingcar = ivShoppingcar;
    }

    private int ivHome;
    private int ivCostomer;
    private int ivShoppingcar;


    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    private String[] titles = new String[]{"简介", "课程", "评论"};


    @BindingAdapter({"app:setNavigator"})
    public  static  void setCommonNavigator(MagicIndicator magicIndicator,String[] titles){

        //导航栏
        CommonNavigator commonNavigator = new CommonNavigator(magicIndicator.getContext());
        //ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(22);
                simplePagerTitleView.getPaint().setFakeBoldText(true);
                simplePagerTitleView.setNormalColor(magicIndicator.getContext().getResources().getColor(R.color.tab_unchecked));
                simplePagerTitleView.setSelectedColor(magicIndicator.getContext().getResources().getColor(R.color.black));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 8));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 5));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(3.0f));
                indicator.setColors(magicIndicator.getContext().getResources().getColor(R.color.colorAccent));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);

    }




    /**
     * 设置本地图片
     * */
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(AppCompatImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
