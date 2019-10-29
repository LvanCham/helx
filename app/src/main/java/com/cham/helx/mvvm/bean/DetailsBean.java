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


    public CommonNavigator getCommonNavigator() {
        return commonNavigator;
    }

    public void setCommonNavigator(CommonNavigator commonNavigator) {
        this.commonNavigator = commonNavigator;
    }

    private CommonNavigator commonNavigator ;




    @BindingAdapter({"setnavigator"})
    public  static  void setCommonNavigator(MagicIndicator magicIndicator,CommonNavigator commonNavigator){
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
