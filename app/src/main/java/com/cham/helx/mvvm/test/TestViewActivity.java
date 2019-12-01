package com.cham.helx.mvvm.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cham.helx.R;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Hello World
 * Date: 2019/11/5
 * Author: Cham
 */
public class TestViewActivity extends AppCompatActivity {

    private String TAG   ="TestViewActivity";

    @BindView(R.id.FButton)
    FloatingActionButton FButton;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private List<String> mData = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    @BindView(R.id.rcy_content)
    RecyclerView rcyContent;

    private BaseMultipleAdapter<String> commonAdapter;

    private Handler handler= new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaa);
        ButterKnife.bind(this);

        for (int i = 0; i < 30; i++) {
            mData.add("1");
        }




        commonAdapter = new BaseMultipleAdapter<>(this,R.layout.item_shop_car,mData);
        commonAdapter.setEmptyView(R.layout.item_empty);
//        commonAdapter.setHeadView(R.layout.item_head);
//        commonAdapter.setFooterView(R.layout.item_home_footer);

        rcyContent.setAdapter(commonAdapter);
        rcyContent.setHasFixedSize(true);

        FButton.setOnClickListener(v -> {
            commonAdapter.upData(mData2);
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                Log.e(TAG, "onRefresh: "+mData.size() );

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                        mData.clear();
                        for (int i = 0; i < 30; i++) {
                            mData.add("1");
                        }
                        Log.e(TAG, "run: "+mData.size() );
                        commonAdapter.upData(mData);
                    }
                },1000);

            }
        });
    }
}
