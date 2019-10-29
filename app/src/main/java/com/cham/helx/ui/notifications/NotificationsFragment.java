package com.cham.helx.ui.notifications;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cham.helx.R;

import com.cham.helx.utils.LiveDataBus;
import com.cham.helx.utils.SoundPoolHelper;
import com.cham.helx.utils.SpUtils;
import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NotificationsFragment extends Fragment {

    private static String TAG = "NotificationsFragment";
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text7)
    TextView text7;
    Unbinder unbinder;
    private NotificationsViewModel notificationsViewModel;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " );
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //1
        Log.e(TAG, "onAttach: " );
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );



        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        SoundPoolHelper.getInstance(getActivity()).init();
        unbinder = ButterKnife.bind(this, root);
        initView(root);
        return root;
    }

    private void initView(View  root) {

        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("aaa","sssss");
        Log.e(TAG, "onSaveInstanceState: " + outState );


    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }




    @OnClick({R.id.text1,R.id.text2,R.id.text3,R.id.text4,R.id.text5,R.id.text6,R.id.text7})
    public void OnClickT(View view){
        switch (view.getId()){
            case R.id.text1:
               MediaPlayer.create(getActivity(), R.raw.car_up).getDuration() ;
                Log.e(TAG, "获取时长: "+   MediaPlayer.create(getActivity(), R.raw.car_up).getDuration()  );
                SoundPoolHelper.getInstance(getActivity()).playSound(0);

                break;
            case R.id.text2:

                SoundPoolHelper.getInstance(getActivity()).playSound(1);
                break;
            case R.id.text3:
                SoundPoolHelper.getInstance(getActivity()).playSound(2);
                break;
            case R.id.text4:
                SoundPoolHelper.getInstance(getActivity()).playSound(3);
                break;
            case R.id.text5:
                SoundPoolHelper.getInstance(getActivity()).playSound(4);
                break;
            case R.id.text6:

                SoundPoolHelper.getInstance(getActivity()).playSound(5);
                break;
            case R.id.text7:
                SoundPoolHelper.getInstance(getActivity()).playSound(6);
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
        unbinder.unbind();
    }
}