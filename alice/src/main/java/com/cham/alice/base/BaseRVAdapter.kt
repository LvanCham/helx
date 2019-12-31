package com.cham.alice.base

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Hello World
 * Date: 2019/12/10
 * Author: Cham
 */
abstract class BaseRVAdapter< T : ViewDataBinding, H: ViewDataBinding, M: ViewDataBinding, F: ViewDataBinding,D>()
    : RecyclerView.Adapter<BaseBindingViewHolder<*>>() {
    open val TAG = this.javaClass.simpleName
    lateinit var mContext:Context
    private lateinit var mData:MutableList<D>
    private  var layoutIds:Int =0
    constructor( context:Context,  data:MutableList<D>,
                 @LayoutRes layout:Int) : this() {
        mContext =context
        layoutIds=layout
        mData = mutableListOf()
        mData.addAll(data)
    }

     private val mHeadView     = 0x00000111
     private val mFooterView   = 0x00000222
     private val mEmptyView    = 0x00000333
     private val mContentView  = 0x00000444

    private var whetherEmpty :Boolean =true
    private var mHeadIds :Int =0
    private var mFooterIds :Int =0
    private var mEmptyIds:Int =0

    open fun setEmptyView(@LayoutRes layout:Int ){
        mEmptyIds =layout
    }
    open fun setHeadView(@LayoutRes layout:Int){
        mHeadIds =layout
    }
    open fun setFooterView(@LayoutRes layout:Int){
        mFooterIds =layout
    }
    private var viewHolder:BaseBindingViewHolder<*>? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder<*> {
          viewHolder = when(viewType){
            mHeadView->{
                BaseBindingViewHolder.createHolder<H>(mContext,parent,mHeadIds)
            }
            mFooterView->{
                BaseBindingViewHolder.createHolder<F>(mContext,parent,mFooterIds)
            }
            mEmptyView->{
                BaseBindingViewHolder.createHolder<M>(mContext,parent,mEmptyIds)
            }
            else->{
                BaseBindingViewHolder.createHolder<T>(mContext,parent,layoutIds)
            }
        }
        return viewHolder!!
    }
    override fun onBindViewHolder(holder: BaseBindingViewHolder<*>, position: Int) {
        when(holder.itemViewType){
            mHeadView ->{
                convertHead(holder as BaseBindingViewHolder<H> )
            }
            mFooterView ->{
                converFooter(holder as BaseBindingViewHolder<F>,mData)
            }
            mEmptyView ->{
                convertEmpty(holder as BaseBindingViewHolder<M>)
            }
            mContentView->{
               convert(
                   helper = holder as BaseBindingViewHolder<T>,
                   item = getItem(position - getHeaderLayoutCount()),
                   position = position - getHeaderLayoutCount()
               )
            }
        }
    }
    abstract  fun convert(helper: BaseBindingViewHolder<T>, item:D?,position: Int)
    private fun getHeaderLayoutCount(): Int {
        return if (mHeadIds == 0 ) {
            0
        } else 1
    }
    @Nullable
    fun getItem(@IntRange(from = 0) position: Int) :D?{
        return if (position >= 0 && position < mData.size)
            mData[position]
        else
            null
    }
    private fun getEmptyViewCount(): Int {
        if (mEmptyIds == 0) {
            return 0
        }
        return if (mData.size != 0) {
            0
        } else {
            1
        }
    }
    override fun getItemCount(): Int {
        var count = 0
        if (getEmptyViewCount() == 1) {
            count++
        } else {
            if (mHeadIds != 0) {
                count++
            }
            if (mFooterIds != 0) {
                count++
            }
            count += mData.size
        }
        return count
    }
    override fun getItemViewType(position: Int): Int {
        return if (getEmptyViewCount() == 1) {
            mEmptyView
        } else {
            if (mHeadIds != 0 && position == 0) {
                mHeadView
            } else if (mFooterIds != 0 && position == itemCount - 1) {
                mFooterView
            } else {
                mContentView
            }
        }
    }
    fun remove( position: Int) {
        mData.removeAt(position)
        val internalPosition = position + getHeaderLayoutCount()
        notifyItemRemoved(internalPosition)
        notifyItemRangeChanged(internalPosition, mData.size - internalPosition)
        notifyDataSetChanged()
    }
    fun removeaNotiyAll( position: Int) {
        mData.removeAt(position)
        notifyDataSetChanged()
    }
    fun upData(datas: List<D>?) {
        whetherEmpty = if (mData.isNotEmpty()) {
            mData.clear()
            notifyDataSetChanged()
            false
        } else {
            true
        }

        if (datas != null && datas.isNotEmpty()) {
            if (itemCount == 1 && whetherEmpty) {
                notifyItemRemoved(0)
                whetherEmpty = false
            }
            var count = 0
            mData.addAll(datas)
            if (mHeadIds != 0) {
                count++
            }
            if (mFooterIds != 0) {
                count++
            } else {
                count = datas.size
            }
            notifyItemRangeInserted(0, count)
            notifyItemChanged(itemCount)

        } else {
            notifyItemInserted(0)
            notifyItemChanged(itemCount)

        }
    }
    fun  addData(data:List<D>?){
        val intSize =mData.size
        data?.let {
            mData.addAll(it)
           notifyItemRangeInserted(intSize,it.size)
        }

    }
    fun  addDataNotiyAll(data:List<D>?){

        data?.let {
            mData.addAll(it)
            notifyDataSetChanged()
        }

    }
    open fun convertEmpty(helper: BaseBindingViewHolder<M>){}
    open fun convertHead(helper: BaseBindingViewHolder<H>){}
    open fun converFooter(helper: BaseBindingViewHolder<F>,mData:MutableList<D>){}

    fun getRvData():MutableList<D>{
        return mData
    }


}


