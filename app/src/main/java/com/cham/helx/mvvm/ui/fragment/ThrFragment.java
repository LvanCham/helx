package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cham.helx.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/14
 * Author: Cham
 */
public class ThrFragment extends SupportFragment {

    public static ThrFragment newInstance() {

        Bundle args = new Bundle();

        ThrFragment fragment = new ThrFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemtn_thr, container, false);
        return view;
    }
}
