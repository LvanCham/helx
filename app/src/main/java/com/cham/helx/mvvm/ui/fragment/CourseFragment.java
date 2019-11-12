package com.cham.helx.mvvm.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cham.helx.R;
import com.cham.helx.madapter.BaseViewHolder;
import com.cham.helx.madapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Hello World
 * Date: 2019/10/19
 * Author: Cham
 * 课程页面
 */
public class CourseFragment extends SupportFragment {


    private String TAG = "CourseFragment";
    @BindView(R.id.rcy_comment)
    RecyclerView rcyComment;

    private Unbinder mUnbinder;
    public static CourseFragment newInstance() {

        Bundle args = new Bundle();

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        mUnbinder= ButterKnife.bind(this, view);
        initView();

        return view;
    }
    private void initView(){
        Log.e(TAG, "initView: " );
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add(""+i);
        }
        rcyComment.setAdapter(new CommonAdapter<String>(getContext(),R.layout.item_details_course,mData) {

            @Override
            public void convert(BaseViewHolder holder, String s, int pot) {

            }
        });
        rcyComment.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
    }
}
