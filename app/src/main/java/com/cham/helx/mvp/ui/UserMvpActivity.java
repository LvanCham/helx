package com.cham.helx.mvp.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;


import androidx.annotation.Nullable;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.blankj.utilcode.util.BarUtils;

import com.bumptech.glide.Glide;
import com.cham.helx.R;

import com.cham.helx.base.BaseApplication;
import com.cham.helx.base.BaseMVPActivity;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.mvp.entity.BannerBean;
import com.cham.helx.mvp.contract.UserContract;

import com.cham.helx.mvp.di.component.DaggerUserComponent;
import com.cham.helx.mvp.presenter.UserPresenter;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.stx.xhb.androidx.XBanner;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;



/**
 * Hello World
 * Date: 2019/9/21
 * Author: Cham
 */
public class UserMvpActivity extends BaseMVPActivity<UserPresenter>  implements UserContract.View {

    @BindView(R.id.xbanner)
    XBanner mXBanner;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tablayout)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.item_rcy)
    RecyclerView itemRcy;

    @Inject
    List<BannerBean> mBannerData;

    private RecyclerView.Adapter mAdapter;

    @Inject
    List<String> mData;

    @Inject
    LinearLayoutManager mLayoutManager;

    private String[] titles = new String[]{"Kantan", "Swifts",
            "Hodan","Fit","Late", "Swifts",
            "Swifts", "Swifts", "Swifts"};
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.transparent));
        return R.layout.activity_user;
    }

    @Override
    public void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mXBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mXBanner.stopAutoPlay();
    }


    @Override
    public void onInject() {
        DaggerUserComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent())
                .view(this)
                .build()
                .InjectAty(this);

        Log.e(TAG, "onInject: " );


    }

    @Override
    public void onInitMvpData() {
        Log.e(TAG, "initDatas: ");

        mBannerData.add(new BannerBean(R.mipmap.splash_o));
        mBannerData.add(new BannerBean(R.mipmap.splash_t));
        mBannerData.add(new BannerBean(R.mipmap.splash_tr));
        mBannerData.add(new BannerBean(R.mipmap.splash_q));
        mBannerData.add(new BannerBean(R.mipmap.splash_w));
        mBannerData.add(new BannerBean(R.mipmap.splash_e));
        mBannerData.add(new BannerBean(R.mipmap.splash_r));

        for (int i = 0; i <30; i++) {
            mData.add("面對疾風吧");
        }

        mXBanner.setBannerData(mBannerData);
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(UserMvpActivity.this).load(((BannerBean) model).getXBannerUrl()).into((ImageView) view);
            }
        });
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(false);  //ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override public int getCount() {
                return titles == null ? 0 : titles.length;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.BLACK);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorP5));
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
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(getResources().getColor(R.color.colorP10));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);


        itemRcy.setLayoutManager(mLayoutManager);


        mAdapter= new CommonAdapter<String>(this,R.layout.item_rcy_1,mData) {
            @Override
            public void convert(BaseViewHolder holder, String o, int pot) {

            }
        };
        itemRcy.setAdapter(mAdapter);
        itemRcy.setHasFixedSize(true);

        mPresenter.dosth();
    }

    @Override
    public Activity getActivity() {
        return UserMvpActivity.this;
    }

    @Override
    public void ShowMsg(String s) {
        Log.e(TAG, "ShowMsg: "+s );
    }
}
