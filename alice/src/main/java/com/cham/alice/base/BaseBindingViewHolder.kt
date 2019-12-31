package com.cham.alice.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Hello World
 * Date: 2019/12/10
 * Author: Cham
 * BaseBinding
 */

class BaseBindingViewHolder<out   M : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
   val binding = DataBindingUtil.bind<M>(itemView)
    companion object {
        fun <T : ViewDataBinding> createHolder(
            context: Context,
            parent: ViewGroup, @LayoutRes layoutId: Int): BaseBindingViewHolder<T> {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
            return BaseBindingViewHolder(itemView)
        }
    }
}