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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.databinding.ActivityDetailsBinding;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.madapter.Viewpager2Adapter;
import com.cham.helx.madapter.Viewpager2FragmentAdapter;
import com.cham.helx.mvvm.bean.DetailsBean;
import com.cham.helx.mvvm.ui.fragment.CommentFragment;
import com.cham.helx.mvvm.ui.fragment.CourseFragment;
import com.cham.helx.mvvm.ui.fragment.IntroFragment;
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
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Hello World
 * Date: 2019/10/18
 * 详情页
 * Author: Cham
 */
public class DetailsActivity extends SupportActivity {


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

    private List<Fragment> mFragmentList;

    private ActivityDetailsBinding detailsBinding;
    private DetailsBean detailsBean;


    private Viewpager2FragmentAdapter mViewpagerAdapter;


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
        //
        mFragmentList = new ArrayList<>();
        mFragmentList.add(IntroFragment.newInstance());
        mFragmentList.add(CourseFragment.newInstance());
        mFragmentList.add(CommentFragment.newInstance());
        mViewpagerAdapter = new Viewpager2FragmentAdapter(this,mFragmentList);



        detailsBean = new DetailsBean();
        detailsBean.setIvHome(R.mipmap.ic_share);
        detailsBean.setIvCostomer(R.mipmap.ic_back);
        detailsBean.setIvShoppingcar(R.mipmap.motorbike);
        detailsBean.setShow(false);

        //导航栏
        CommonNavigator commonNavigator = new CommonNavigator(this);
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
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.tab_unchecked));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.black));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detailsBinding.vp2Details.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(3.0f));
                indicator.setColors(getResources().getColor(R.color.colorAccent));
                return indicator;
            }
        });

        detailsBinding.vp2Details.setAdapter(mViewpagerAdapter);
        detailsBean.setCommonNavigator(commonNavigator);
        detailsBinding.setDetails(detailsBean);


        detailsBinding.vp2Details.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                detailsBinding.magicDetails.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                detailsBinding.magicDetails.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                detailsBinding.magicDetails.onPageScrollStateChanged(state);
            }
        });


        detailsBinding.ivBack.setOnClickListener(v -> finish());


    }



}
