package com.cham.helx.mvvm.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.databinding.ActivityDetailsBinding;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.madapter.Viewpager2Adapter;
import com.cham.helx.mvvm.bean.DetailsBean;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Hello World
 * Date: 2019/10/18
 * 详情页
 * Author: Cham
 */
public class DetailsActivity extends AppCompatActivity implements Viewpager2Adapter.converHolder {


    @BindView(R.id.csl_title)
    ConstraintLayout cslTitle;
    @BindView(R.id.tv_details)
    AppCompatImageView tvDetails;
    @BindView(R.id.dtl_ctb)
    CollapsingToolbarLayout dtlCtb;
    @BindView(R.id.magic_details)
    MagicIndicator magicDetails;
    @BindView(R.id.dtl_appbar)
    AppBarLayout dtlAppbar;

    @BindView(R.id.g_line)
    Guideline gLine;
    @BindView(R.id.iv_home)
    AppCompatImageView ivHome;
    @BindView(R.id.iv_customer)
    AppCompatImageView ivCustomer;
    @BindView(R.id.iv_shoppingcart)
    AppCompatImageView ivShoppingcart;
    @BindView(R.id.tv_home)
    AppCompatTextView tvHome;
    @BindView(R.id.tv_customer)
    AppCompatTextView tvCustomer;
    @BindView(R.id.tv_shoppingcart)
    AppCompatTextView tvShoppingcart;
    @BindView(R.id.btn_add_shoppongcart)
    AppCompatButton btnAddShoppongcart;
    @BindView(R.id.btn_buy_now)
    AppCompatButton btnBuyNow;
    @BindView(R.id.csl_bottom)
    ConstraintLayout cslBottom;
    @BindView(R.id.vp2_details)
    ViewPager2 vp2Details;

    private String[] titles = new String[]{"简介", "课程", "评论"};

    private ActivityDetailsBinding detailsBinding;
    private DetailsBean detailsBean;


    private Viewpager2Adapter mViewpagerAdapter;
    private List<String> mAllData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //黑色字体
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.white));
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        ButterKnife.bind(this);
        initVeiw();
    }


    private void initVeiw() {
        mAllData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mAllData.add("");
        }
        detailsBean = new DetailsBean();
        detailsBean.setIvHome(R.mipmap.ic_share);
        detailsBean.setIvCostomer(R.mipmap.ic_back);
        detailsBean.setIvShoppingcar(R.mipmap.motorbike);
        detailsBean.setShow(false);

        mViewpagerAdapter = new Viewpager2Adapter(this, titles, R.layout.item_rcy);
        mViewpagerAdapter.setcConverHolder(this);

        detailsBinding.vp2Details.setAdapter(mViewpagerAdapter);

        detailsBean.setTitles(titles);
        detailsBinding.setDetails(detailsBean);


    }


    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setAppText(R.id.tv__rcy_title, titles[position]);
        RecyclerView mRcyItem = holder.getView(R.id.item_rcy);
        mRcyItem.setAdapter(new CommonAdapter<String>(this, R.layout.item_all_tem, mAllData) {
            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {
                holder.setAppText(R.id.tv_naem, titles[position]);
            }
        });
        mRcyItem.setHasFixedSize(true);
    }
}
