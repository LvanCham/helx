package com.cham.helx.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.GsonUtils;
import com.cham.helx.R;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.mvp.entity.PoetryEntity;
import com.cham.helx.widget.PillarView;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;



public class DashboardFragment extends Fragment {

    private static  String TAG ="DashboardFragment";
    private DashboardViewModel dashboardViewModel;

    private boolean isNavigationViewInit = false;//记录是否已经初始化过一次视图
    private View lastView = null;//记录上次创建的view


    private   TextView textView;
    private RecyclerView recyclerView;

    private List<Integer> mData = new ArrayList<>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " );
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: " );
    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        if(lastView ==null){
           // lastView  = inflater.inflate(R.layout.fragment_dashboard, container, false);
          lastView = super.onCreateView(inflater,container,savedInstanceState);
        }
        View root =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        lastView =root;
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(!isNavigationViewInit){
            Log.e(TAG, "onViewCreated: " );
            //初始化过视图则不再进行view和data初始化
            super.onViewCreated(view, savedInstanceState);
            isNavigationViewInit = true;
        }
        textView = lastView.findViewById(R.id.text_dashboard);
        recyclerView = lastView.findViewById(R.id.rcy_timealxe);

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);


        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        for (int i = 0; i <9 ; i++) {
            mData.add(i*90);
        }

        recyclerView.setAdapter(new CommonAdapter<Integer>(getContext(),R.layout.item_time_alxe,mData) {
            @Override
            public void convert(BaseViewHolder holder, Integer s, int pot) {
                AppCompatTextView textView = holder.getView(R.id.tv_bg);
                if(pot==mData.size()-1){
                    textView.setVisibility(View.GONE);
                }else {
                    textView.setVisibility(View.VISIBLE);
                }
                PillarView pillarView = holder.getView(R.id.pina);
                pillarView.setmHeight(s);
            }
        });
        recyclerView.setHasFixedSize(true);

    }
}