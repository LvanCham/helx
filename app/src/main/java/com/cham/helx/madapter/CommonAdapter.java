package com.cham.helx.madapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.cham.helx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello World
 * Date: 2019/5/22
 * Author: Cham
 * 通用Adapter
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private BaseViewHolder viewHolder;


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }



    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

            viewHolder = BaseViewHolder.createViewHolder(mContext, parent, mLayoutId);


        return viewHolder;
    }

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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);

    }


    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public abstract void convert(BaseViewHolder holder, T t, int pot);



    /**
     * @return
     */
    @Override
    public int getItemCount() {
        int count;
            count= mDatas.size();

        return count;
    }

}