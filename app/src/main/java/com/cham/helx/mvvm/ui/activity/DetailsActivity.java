package com.cham.helx.mvvm.ui.activity;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.cham.helx.R;
import com.cham.helx.databinding.ActivityDetailsBinding;
import com.cham.helx.madapter.Viewpager2FragmentAdapter;
import com.cham.helx.mvvm.base.BaseMvvmActivity;
import com.cham.helx.mvvm.bean.DetailsBean;
import com.cham.helx.mvvm.ui.fragment.CommentFragment;
import com.cham.helx.mvvm.ui.fragment.CourseFragment;
import com.cham.helx.mvvm.ui.fragment.IntroFragment;
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
 * Date: 2019/10/18
 * 详情页
 * Author: Cham
 */
public class DetailsActivity extends BaseMvvmActivity<ActivityDetailsBinding> {



    private String[] titles = new String[]{"简介", "课程", "评论"};
    private List<Fragment> mFragmentList;
    private DetailsBean detailsBean;
    private Viewpager2FragmentAdapter mViewpagerAdapter;

    @Override
    public int onLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.white));
        initVeiw();
    }


    private void initVeiw() {
        /**
         * 宽度适配后 pt  = px
         * */
        XLog.e(AdaptScreenUtils.px2Pt(10));
        XLog.e(AdaptScreenUtils.pt2Px(10));
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
                        binding.vp2Details.setCurrentItem(index);
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
        binding.vp2Details.setAdapter(mViewpagerAdapter);
        detailsBean.setCommonNavigator(commonNavigator);
        binding.setDetails(detailsBean);

        binding.vp2Details.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                binding.magicDetails.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.magicDetails.onPageSelected(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                binding.magicDetails.onPageScrollStateChanged(state);
            }
        });

        binding.ivBack.setOnClickListener(v -> finish());




    }



}
