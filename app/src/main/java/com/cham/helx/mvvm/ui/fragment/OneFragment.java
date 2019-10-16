package com.cham.helx.mvvm.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.cham.helx.R;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.madapter.MultipleItemAdapter;
import com.cham.helx.madapter.Viewpager2Adapter;
import com.cham.helx.mvp.entity.BannerBean;
import com.cham.helx.mvp.ui.UserMvpActivity;
import com.cham.helx.utils.ScaleTransitionPagerTitleView;
import com.stx.xhb.androidx.XBanner;

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
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 */
public class OneFragment extends SupportFragment implements  SwipeRefreshLayout.OnRefreshListener ,
        MultipleItemAdapter.OnMulyipleBindView, Viewpager2Adapter.converHolder {


    @BindView(R.id.tv_base_loaction)
    AppCompatTextView tvBaseLoaction;
    @BindView(R.id.ed_s)
    AppCompatEditText edS;
    @BindView(R.id.appCompatTextView)
    AppCompatTextView appCompatTextView;
    @BindView(R.id.cs_title)
    ConstraintLayout csTitle;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.rcy_content)
    RecyclerView rcyContent;
    @BindView(R.id.sw_layout)
    SwipeRefreshLayout swLayout;
    private   Unbinder unbinder;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String[] titles = new String[]{"全部", "一年级",
            "二年级","三年级","四年级", "五年级",
            "六年级", "七年级", "八年级", "九年级"};
    protected Context mContext;
    private  MultipleItemAdapter multipleItemAdapter;
    private ViewPager2 viewPager2 ;
    private Viewpager2Adapter mViewpagerAdapter;
    /**
     * banner 数据
     * */
    private List<BannerBean> mBannerData;

    /**
     * 免费课程数据
     * */
    private List<String> mFreeData;

    /**
     * 免费课程数据
     * */
    private List<String> mSpecialData;

    /**
     * 全部课程
     * */
    private List<String> mAllData;

    public static OneFragment newInstance() {
        Bundle args = new Bundle();
        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext =context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder= ButterKnife.bind(this, view);
        initVeiw();
        return view;
    }

    private void initVeiw(){
        //banner 数据
        mBannerData = new ArrayList<>();
        mBannerData.add(new BannerBean(R.mipmap.splash_o));
        mBannerData.add(new BannerBean(R.mipmap.splash_t));
        mBannerData.add(new BannerBean(R.mipmap.splash_tr));
        mBannerData.add(new BannerBean(R.mipmap.splash_q));
        mBannerData.add(new BannerBean(R.mipmap.splash_w));
        mBannerData.add(new BannerBean(R.mipmap.splash_e));
        mBannerData.add(new BannerBean(R.mipmap.splash_r));

        //免费课程
        mFreeData = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            mFreeData.add("");
        }
        //专项课程
        mSpecialData=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            mSpecialData.add("");
        }
        //全部课程
        mAllData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mAllData.add("");
        }

        //导航栏
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        //ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override public int getCount() {
                return titles == null ? 0 : titles.length;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(22);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.tab_unchecked));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.black));
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
                indicator.setLineHeight(UIUtil.dip2px(context, 8));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 5));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(3.0f));
                indicator.setColors(getResources().getColor(R.color.colorP9));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);

        //下拉刷新控件
        swLayout.setColorSchemeResources(R.color.colorP4,
                R.color.colorP15,R.color.colorP10,
               R.color.colorP11);

        swLayout.setOnRefreshListener(this);
        //设置进度View下拉的起始点和结束点，scale 是指设置是否需要放大或者缩小动画
        swLayout.setProgressViewOffset(false, -0, 300);
        //设置进度View下拉的结束点，scale 是指设置是否需要放大或者缩小动画
        swLayout.setProgressViewEndTarget(true, 200);
        //设置触发刷新的距离
        swLayout.setDistanceToTriggerSync(200);


        multipleItemAdapter=new MultipleItemAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcyContent.setLayoutManager(linearLayoutManager);
        multipleItemAdapter.setBannerView(R.layout.item_banner);
        multipleItemAdapter.setFreeClassVeiwView(R.layout.item_free);
        multipleItemAdapter.setContentClassView(R.layout.item_ontent_class);
        multipleItemAdapter.setSpecialClassView(R.layout.item_special_class);
        multipleItemAdapter.setFooterView(R.layout.item_home_footer);
        multipleItemAdapter.setOnMulyipleBindView(this);
        rcyContent.setAdapter(multipleItemAdapter);
        rcyContent.setHasFixedSize(true);

        /**
         * viewpager2 Adapter
         * */
        mViewpagerAdapter = new Viewpager2Adapter(mContext,titles,R.layout.item_rcy);
        mViewpagerAdapter.setcConverHolder(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swLayout.setVisibility(View.VISIBLE);
                swLayout.setRefreshing(false); // close refresh animator
            }
        },5000);

    }

    @Override
    public void onBindBanner(BaseViewHolder baseViewHolder, int position) {
        XBanner mXBanner = baseViewHolder.getView(R.id.xbanner);
        mXBanner.setBannerData(mBannerData);
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mContext).load(((BannerBean) model).getXBannerUrl()).into((ImageView) view);
            }
        });
    }

    @Override
    public void onBindFreeClass(BaseViewHolder baseViewHolder, int position) {
        RecyclerView mRcyItem = baseViewHolder.getView(R.id.rcy_free);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcyItem.setLayoutManager(linearLayoutManager2);

        mRcyItem.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_free_class, mFreeData) {
            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });
        mRcyItem.setHasFixedSize(true);
    }

    @Override
    public void onBindContentClass(BaseViewHolder baseViewHolder, int position) {
        viewPager2 = baseViewHolder.getView(R.id.viewpager2);
        viewPager2.setAdapter(mViewpagerAdapter);
        // 注册页面变化的回调接口
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mMagicIndicator.onPageSelected(position);
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mMagicIndicator.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                mMagicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onBindSpecialClass(BaseViewHolder baseViewHolder, int position) {
        RecyclerView mRcyItem = baseViewHolder.getView(R.id.rcy_special);
        mRcyItem.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRcyItem.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_special_content, mSpecialData) {
            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setAppText(R.id.tv__rcy_title,titles[position]);
        RecyclerView mRcyItem = holder.getView(R.id.item_rcy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyItem.setLayoutManager(linearLayoutManager);
        mRcyItem.setAdapter(new CommonAdapter<String>(mContext, R.layout.item_all_tem, mAllData) {
            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });

        mRcyItem.setHasFixedSize(true);
    }
}
