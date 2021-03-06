package com.cham.helx.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cham.helx.R;
import com.cham.helx.mvp.ui.UserMvpActivity;
import com.cham.helx.mvp.ui.VideoPlayActivity;
import com.cham.helx.mvvm.MainAty;
import com.cham.helx.mvvm.test.TestViewActivity;
import com.cham.helx.mvvm.ui.activity.BehaviorActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private static String TAG = "HomeFragment";
    @BindView(R.id.btn_video)
    AppCompatButton btnVideo;
    @BindView(R.id.btn_new_aty)
    AppCompatButton btnNewAty;
    @BindView(R.id.text_home)
    TextView textHome;
    @BindView(R.id.chronometer)
    Chronometer chronometer;
    @BindView(R.id.btn_be)
    AppCompatButton btnBe;
    @BindView(R.id.btn_aaa)
    AppCompatButton btnAaa;
    private Unbinder unbinder;
    @BindView(R.id.btn_chack)
    AppCompatButton btnChack;
    private HomeViewModel homeViewModel;

    private TextView textView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);
        textView = root.findViewById(R.id.text_home);
        initSth();
        return root;
    }


    private void initSth() {

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        btnChack.setOnClickListener(v -> {
            homeViewModel.setData("AAA");
            Log.e(TAG, "获取计时器时间 " + chronometer.getBase());
            Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), UserMvpActivity.class));

        });

        btnVideo.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), VideoPlayActivity.class));
        });

        btnNewAty.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), MainAty.class));

        });

        btnAaa.setOnClickListener(v-> Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), TestViewActivity.class)));

        btnBe.setOnClickListener(v -> Objects.requireNonNull(getActivity())
                .startActivity(new Intent(getActivity(), BehaviorActivity.class)));

        chronometer.setFormat("计时：%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}