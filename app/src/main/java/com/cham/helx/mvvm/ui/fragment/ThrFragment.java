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

import com.cham.helx.BR;
import com.cham.helx.R;
import com.cham.helx.mvvm.test.Test;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 */
public class ThrFragment extends SupportFragment  {
    protected ViewDataBinding binding;
    private Unbinder mUnbinder;
    @BindView(R.id.tv_test)
    AppCompatTextView tvTest;
    @BindView(R.id.ed_test)
    AppCompatEditText edTest;
    @BindView(R.id.btn_1)
    AppCompatButton btn1;
    private String TAG = "ThrFragment";



    public static ThrFragment newInstance() {

        Bundle args = new Bundle();

        ThrFragment fragment = new ThrFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thr, container, false);
        mUnbinder =ButterKnife.bind(this, binding.getRoot());
        initData();
        return binding.getRoot();
    }

    private void initData() {
        Test test = new Test();
        binding.setVariable(BR.test, test);
        binding.executePendingBindings();
        btn1.setOnClickListener(v -> {
            Log.e(TAG, "initData: "+test.getS1()  + " ----" );

        });


        edTest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.setVariable(BR.test, test);
                binding.executePendingBindings();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding = null;
        }
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }


}
