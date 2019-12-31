package com.cham.alice.ui.one

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cham.alice.R
import com.cham.alice.base.BaseFragment
import com.cham.alice.base.NoViewModel
import com.cham.alice.databinding.FragmentHomeBinding
import com.cham.alice.entity.HomeListEntity

/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class OneFragment :BaseFragment<OneViewModel,FragmentHomeBinding>(),View.OnClickListener {

    private  lateinit var  mAdapter :OneAdapter

    private   var mData  = mutableListOf<HomeListEntity>()

    override fun onClick(v: View?) {
    }
    companion object {
        fun newInstance(): OneFragment {
            return OneFragment()
        }
    }
    override fun providerVMClass(): Class<OneViewModel> =OneViewModel::class.java
    override fun initLayout(): Int = R.layout.fragment_home
    override fun initView(savedInstanceState: Bundle?) {
        binding.listener=this
        Glide.with(this).asGif().diskCacheStrategy(DiskCacheStrategy.DATA).load(R.mipmap.duola).into(binding.ivAvatar)

        viewModel.getHomeList()
        mAdapter= OneAdapter(mContext,mData)
        mAdapter.setFooterView(R.layout.item_load_more)

        binding.rvHome.adapter = mAdapter
        binding.rvHome.setHasFixedSize(true)
        viewModel.mHomeList.observe(this, Observer {
            mAdapter.upData(it.data.datas)
        })



    }



}