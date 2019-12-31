package com.cham.alice.ui.one

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cham.alice.R
import com.cham.alice.base.BaseBindingViewHolder
import com.cham.alice.base.BaseRVAdapter
import com.cham.alice.databinding.ItemHomeContentBinding
import com.cham.alice.databinding.ItemLoadMoreBinding
import com.cham.alice.entity.HomeListEntity

/**
 * Hello World
 * Date: 2019/12/31
 * Author: Cham
 */
class OneAdapter(mContext:Context, data:MutableList<HomeListEntity>)  :BaseRVAdapter<ItemHomeContentBinding,
        ViewDataBinding,
        ViewDataBinding,
        ItemLoadMoreBinding,HomeListEntity>(mContext,data, R.layout.item_home_content){
    override fun convert(helper: BaseBindingViewHolder<ItemHomeContentBinding>, item: HomeListEntity?, position: Int) {
        helper.binding?.let {
            it.data =item
            it.tvNum.text="$position."
        }
    }

    override fun converFooter(helper: BaseBindingViewHolder<ItemLoadMoreBinding>, mData: MutableList<HomeListEntity>) {
        Glide.with(mContext).asGif().diskCacheStrategy(DiskCacheStrategy.DATA).load(R.mipmap.g_more).into(helper.binding!!.ivLoadMore)
    }
}