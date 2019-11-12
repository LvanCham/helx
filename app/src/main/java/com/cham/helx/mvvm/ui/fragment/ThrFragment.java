package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.cham.helx.BR;
import com.cham.helx.R;
import com.cham.helx.databinding.FragmentThrBinding;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;
import com.cham.helx.mvvm.base.BaseMvvmFragment;
import com.cham.helx.mvvm.test.Test;

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
public class ThrFragment extends BaseMvvmFragment<FragmentThrBinding> {


    public static ThrFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ThrFragment fragment = new ThrFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private List<String> mData =new ArrayList<>(20);
    @Override
    public int onLayout() {
        return R.layout.fragment_thr;
    }

    @Override
    public void initView() {

        for (int i = 0; i <20 ; i++) {
            mData.add("");
        }

        binding.rcyShopcar.setAdapter(new CommonAdapter<String>(_mActivity,R.layout.item_shop_car,mData) {


            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });
    }
}
