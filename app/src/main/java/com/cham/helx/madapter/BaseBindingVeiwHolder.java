package com.cham.helx.madapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Hello World
 * Date: 2019/10/23
 * Author: Cham
 * databing holder
 */
public class BaseBindingVeiwHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public  T getBinding() {
        return binding;
    }

    private  T binding;

    public BaseBindingVeiwHolder(@NonNull View itemView) {
        super(itemView);
        //通过DataBindingUtil.bind()方法，使DataBinding绑定布局，并且返回ViewDataBinding的子类对象
        binding = DataBindingUtil.bind(itemView);

    }


    public static BaseBindingVeiwHolder createHolder(Context context,
                                               ViewGroup parent, int layoutId){

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        return  new BaseBindingVeiwHolder(itemView);
    }
}
