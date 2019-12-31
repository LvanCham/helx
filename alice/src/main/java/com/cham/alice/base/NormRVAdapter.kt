package com.cham.alice.base

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Hello World
 * Date: 2019/12/20
 * Author: Cham
 * 普通适配器
 */
abstract class NormRVAdapter<T : ViewDataBinding, D>(
    private val mContext: Context,
    protected var mDatas: MutableList<D>
) :
    RecyclerView.Adapter<BaseBindingViewHolder<T>>() {

    abstract val itemLayout: Int


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<T> {


        return BaseBindingViewHolder.createHolder(mContext, parent, itemLayout)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<T>, position: Int) {
        onBindItem(holder, position)

    }

    abstract fun onBindItem(holder: BaseBindingViewHolder<T>, position: Int)

    fun updateData(dataSet: List<D>) {
        this.mDatas.clear()
        appendData(dataSet)
    }

    fun appendData(dataSet: List<D>?) {
        if (dataSet != null && dataSet.isNotEmpty()) {
            this.mDatas.addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {

        return if (mDatas.size == 0) {
            0
        } else {
            mDatas.size
        }
    }


}
