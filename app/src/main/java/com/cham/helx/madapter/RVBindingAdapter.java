package com.cham.helx.madapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

/**
 * Hello World
 * Date: 2019/10/23
 * Author: Cham
 */
public abstract class RVBindingAdapter<T extends ViewDataBinding,D> extends RecyclerView.Adapter<BaseBindingVeiwHolder<T>> {

    protected List<D> mDatas;
    private Context mContext;


    public RVBindingAdapter(Context context,List<D> datas){
        mContext=context;
        mDatas =datas;
    }


    @NonNull
    @Override
    public BaseBindingVeiwHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseBindingVeiwHolder.createHolder(mContext,parent, getItemLayout());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingVeiwHolder<T> holder, int position) {
        onBindItem(holder,position);
    }

     public abstract int getItemLayout();

    public abstract void onBindItem( BaseBindingVeiwHolder<T> holder, int position);

    public void updateData(List dataSet) {
        this.mDatas.clear();
        appendData(dataSet);
    }

    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.mDatas.addAll(dataSet);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if(mDatas.isEmpty()){
            return  0;
        }else {
            return mDatas.size();
        }
    }
}
