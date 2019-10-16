package com.cham.helx.madapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Hello World
 * Date: 2019/10/16
 * Author: Cham
 */
public class Viewpager2Adapter  extends RecyclerView.Adapter<BaseViewHolder> {



    private Context mContext;
    private BaseViewHolder viewViewHolder;
    private String[] mTitle;
    private int mLayoutId ;

    private  converHolder converHolder ;
    public  Viewpager2Adapter(Context c,String[] title,int mLayoutId){
        mContext=c;
        mTitle =title;
        this.mLayoutId=mLayoutId;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewViewHolder =BaseViewHolder.createViewHolder2(mContext, parent, mLayoutId);
        return viewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        converHolder.convert(holder,position);
    }

    public void setcConverHolder(converHolder converHolder1){
        converHolder =converHolder1;
    }
    public  interface     converHolder{
        void    convert( BaseViewHolder holder, int position);
    }
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
