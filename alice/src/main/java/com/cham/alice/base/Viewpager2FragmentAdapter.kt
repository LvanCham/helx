package com.cham.alice.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Hello World
 * Date: 2019/11/11
 * Author: Cham
 */
class Viewpager2FragmentAdapter(var fragmentActivity: FragmentActivity,var fragmentList:MutableList<Fragment>) :
        FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
     return  fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return  fragmentList[position]
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}