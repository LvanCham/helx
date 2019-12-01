package com.cham.helx.mvvm.test;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cham.helx.madapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello World
 * Date: 2019/12/1
 * Author: Cham
 */
public class BaseMultipleAdapter <T> extends RecyclerView.Adapter<BaseViewHolder> {


    private String TAG = "BaseMultipleAdapter";

    private Context  mContext;
    //内容布局
    private int mContentLayoutIds;
    //Head布局
    private int mHeadLayoutIds =0;
    //Footer布局
    private int mFooterLayoutIds =0;
    //空视图布局
    private int mEmptyLayoutIds =0;


    private List<T> mDatas;

    public BaseMultipleAdapter(Context context, int layoutIds, List<T> datas ){
        mContext = context;
        mContentLayoutIds = layoutIds;
        mDatas = new ArrayList<>();
        mDatas.addAll(datas);
    }

    public  void setEmptyView(int layout){
        mEmptyLayoutIds= layout;
    }
    public  void setHeadView(int layout){
        mHeadLayoutIds= layout;
    }
    public  void setFooterView(int layout){
        mFooterLayoutIds= layout;
    }



    //先不要Loading 布局
    public static final int HEADER_VIEW = 0x00000111;
    public static final int FOOTER_VIEW = 0x00000333;
    public static final int EMPTY_VIEW = 0x00000555;

    private BaseViewHolder viewHolder;
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //这里没什么好说的
        switch ( viewType){
            case  HEADER_VIEW:
                viewHolder = BaseViewHolder.createViewHolder(mContext,parent, mHeadLayoutIds);
                break;
            case  FOOTER_VIEW:
                viewHolder = BaseViewHolder.createViewHolder(mContext,parent, mFooterLayoutIds);
                break;
            case  EMPTY_VIEW:
                viewHolder = BaseViewHolder.createViewHolder(mContext,parent, mEmptyLayoutIds);
                break;
            default:
                viewHolder = BaseViewHolder.createViewHolder(mContext,parent, mContentLayoutIds);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(getEmptyViewCount() ==1){
            count ++;
        }else {
            if(mHeadLayoutIds!=0){
                count ++;
            }
            if(mFooterLayoutIds!=0){
                count ++;
            }
            count += mDatas.size();
        }
        return count;
    }


    @Override
    public int getItemViewType(int position) {
        if(getEmptyViewCount() ==1){
            return EMPTY_VIEW;
        }else {
            if(mHeadLayoutIds !=0 && position ==0){
                return HEADER_VIEW;
            }else  if(mFooterLayoutIds!=0 && position ==getItemCount()-1){
                return FOOTER_VIEW;
            }else {
                //内容
                return super.getItemViewType(position);
            }
        }

    }

    private int getEmptyViewCount(){
        if(mEmptyLayoutIds ==0){
            return  0;
        }
        if(mDatas.size()!=0){
            return  0;
        }else{
            return  1;
        }
    }
    private boolean isEmpty =true;
    public  void upData(List<T> datas){
        if (!mDatas.isEmpty()) {
            Log.e(TAG, "upData: 原来的数据不为空" );
            //子条目布局 --> 恢复默认的布局
//          int size = mDatas.size();
            mDatas.clear();
//           notifyItemRangeRemoved(0, size);
//           notifyItemChanged(getItemCount());
            notifyDataSetChanged();
        }else {
            isEmpty =true;
        }
        if (datas != null && !datas.isEmpty()) {
            Log.e(TAG, "upData: 数据不为空 "  );
           //空布局 --> 恢复默认的布局
            if (getItemCount() == 1 &&isEmpty) {
                Log.e(TAG, "移除空布局 --> 恢复默认的布局 "  );
                notifyItemRemoved(0);
                isEmpty =false;
            }


            //刷新，新添加的数据
            int count =0 ;
            mDatas.addAll(datas);
            if(mHeadLayoutIds!=0){
                count++;
            }
            if(mFooterLayoutIds!=0){
                count++;
            }else {
                count = datas.size();
            }
            notifyItemRangeInserted(0, count);
            notifyItemChanged(getItemCount());

        } else {
            Log.e(TAG, "upData: 设置为空布局"  );
            //空 数据
            notifyItemInserted(0);
            notifyItemChanged(getItemCount());

        }
    }


}
