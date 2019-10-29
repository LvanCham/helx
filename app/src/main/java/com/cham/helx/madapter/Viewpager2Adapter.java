package com.cham.helx.madapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello World
 * Date: 2019/10/16
 * Author: Cham
 * 临时的
 */
public class Viewpager2Adapter  extends RecyclerView.Adapter<BaseViewHolder> {


    private Context mContext;
    private List<String> mTitle =new ArrayList<>();
    private int mLayoutId ;

    private  converHolder converHolder ;
    public  Viewpager2Adapter(Context c,List<String> title,int mLayoutId){
        mContext=c;
        mTitle.addAll(title);
        this.mLayoutId=mLayoutId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseViewHolder.createViewHolder2(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        converHolder.convert(holder,position);
    }

    public void setcConverHolder(converHolder converHolder1){
        converHolder =converHolder1;
    }
    public  interface    converHolder{
        void    convert( BaseViewHolder holder, int position);
    }
    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public void updateData(List dataSet) {
        this.mTitle.clear();
        appendData(dataSet);
    }

    public void appendData(List dataSet) {
        if (dataSet != null && !dataSet.isEmpty()) {
            this.mTitle.addAll(dataSet);
         notifyDataSetChanged();
        }
    }
}
