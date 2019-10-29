package com.cham.helx.madapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cham.helx.R;

/**
 * Hello World
 * Date: 2019/10/15
 * Author: Cham
 */
public class MultipleItemAdapter  extends RecyclerView.Adapter<BaseViewHolder>{

    public MultipleItemAdapter(Context context){
        mContext=context;
    }

    private OnMulyipleBindView onMulyipleBindView;

    //banner
    public static final int TYPE_BANNER = 101;

    //免费课程
    public static final int TYPE_CLASS_FREE = 102;

    //年级内容
    public static final int TYPE_CLASS_CONTENT=103;

    //专项课程
    public static final int TYPE_CLASS_SPECIAL=104;

    //底部
    public static final int TYPE_FOOTER = 105;


    private int bannerView,freeClassVeiw,
            contentClassView,specialClassView,
            footerView;


    private BaseViewHolder BannerViewHolder,FreeViewHolder,
            ContentViewHolder,SpecialViewHolder,
            FooterViewHolder;

    private Context mContext;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(bannerView!=0 && viewType ==TYPE_BANNER){
            BannerViewHolder = BaseViewHolder.createViewHolder(mContext,parent, bannerView);
            return BannerViewHolder;
        }else if(freeClassVeiw!=0 && viewType ==TYPE_CLASS_FREE){
            FreeViewHolder = BaseViewHolder.createViewHolder(mContext,parent,freeClassVeiw);
            return FreeViewHolder;
        }else if(contentClassView!=0 && viewType ==TYPE_CLASS_CONTENT){
            ContentViewHolder = BaseViewHolder.createViewHolder(mContext,parent,contentClassView);
            return ContentViewHolder;
        }else if(specialClassView!=0 && viewType ==TYPE_CLASS_SPECIAL){
            SpecialViewHolder = BaseViewHolder.createViewHolder(mContext,parent,specialClassView);
            return SpecialViewHolder;
        }else {
            FooterViewHolder = BaseViewHolder.createViewHolder(mContext,parent,footerView);
            return FooterViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_BANNER) {
          onMulyipleBindView.onBindBanner(holder,position);
        }else if(getItemViewType(position) == TYPE_CLASS_FREE){
            onMulyipleBindView.onBindFreeClass(holder,position);
        }else if(getItemViewType(position) == TYPE_CLASS_CONTENT){
            onMulyipleBindView.onBindContentClass(holder,position);
        }else if(getItemViewType(position) == TYPE_CLASS_SPECIAL){
            onMulyipleBindView.onBindSpecialClass(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        int count =0;
        if(bannerView!=0){
            count++; }
        if(freeClassVeiw!=0){
            count++; }
        if(contentClassView!=0){
            count++; }
        if (specialClassView!=0){
            count++; }
        if (footerView!=0){
            count++; }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
       if(bannerView != 0 && position == 0){
           return TYPE_BANNER;
       }else if(freeClassVeiw!=0 && position == 1){
            return TYPE_CLASS_FREE;
        }else if(contentClassView!=0 && position == 2){
            return TYPE_CLASS_CONTENT;
        }else if(specialClassView!=0 && position == 3){
            return TYPE_CLASS_SPECIAL;
        }else {
            return TYPE_FOOTER;
        }
    }

    /**
     * 加载View 写死了
     * */
    public void setBannerView( int layoutId){
        if (mContext == null && layoutId < 0) {
            return;
        }
        bannerView =layoutId;
        notifyItemInserted(0);
    }
    public void setFreeClassVeiwView( int layoutId){
        if (mContext == null && layoutId < 0) {
            return;
        }
        freeClassVeiw =layoutId;
        notifyItemInserted(1);
    }
    public void setContentClassView( int layoutId){
        if (mContext == null && layoutId < 0) {
            return;
        }
        contentClassView =layoutId;
        notifyItemInserted(2);
    }
    public void setSpecialClassView(int layoutId){
        if (mContext == null && layoutId < 0) {
            return;
        }
        specialClassView =layoutId;
        notifyItemInserted(3);
    }
    public void setFooterView( int layoutId){
        if (mContext == null && layoutId < 0) {
            return;
        }
        footerView =layoutId;
        notifyItemInserted(getItemCount()-1);
    }


    public void setOnMulyipleBindView(OnMulyipleBindView mulyipleBindView){
        onMulyipleBindView =mulyipleBindView;
    }
    public  interface  OnMulyipleBindView{
        void onBindBanner( BaseViewHolder baseViewHolder, int position);
        void onBindFreeClass( BaseViewHolder baseViewHolder, int position);
        void onBindContentClass( BaseViewHolder baseViewHolder, int position);
        void onBindSpecialClass( BaseViewHolder baseViewHolder, int position);
    }

}
