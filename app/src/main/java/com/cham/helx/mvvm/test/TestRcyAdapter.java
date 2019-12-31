package com.cham.helx.mvvm.test;

import android.content.Context;
import android.view.View;

import com.cham.helx.R;
import com.cham.helx.databinding.ItemCourseNavBinding;
import com.cham.helx.madapter.BaseBindingVeiwHolder;
import com.cham.helx.madapter.RVBindingAdapter;

import java.util.List;

/**
 * Hello World
 * Date: 2019/10/23
 * Author: Cham
 */
public class TestRcyAdapter extends RVBindingAdapter<ItemCourseNavBinding,Test> {


    private OnRcyAdapterclick onRcyAdapterclick;



    public TestRcyAdapter(Context context, List<Test> datas) {
        super(context,  datas);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_course_nav;
    }

    @Override
    public void onBindItem(BaseBindingVeiwHolder<ItemCourseNavBinding> holder, int position) {
        holder.getBinding().setTest(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRcyAdapterclick.itemOnclick(position,mDatas);

            }
        });

    }

    public  void setOnClick(OnRcyAdapterclick RcyAdapterclick){
        onRcyAdapterclick =RcyAdapterclick;
    }

   public interface  OnRcyAdapterclick{
        void itemOnclick(int position,List<Test> datas);
    }

}
