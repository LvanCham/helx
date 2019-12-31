package com.cham.alice.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blankj.utilcode.util.ToastUtils
import com.cham.alice.base.event.AliceMessage

/**
 * Hello World
 * Date: 2019/12/2
 * Author: Cham
 */
abstract class BaseFragment <VM : BaseViewModel,T:ViewDataBinding>:Fragment(), IView {
    open val TAG =this.javaClass.simpleName

    protected lateinit var binding:T
    protected lateinit var viewModel :VM
    protected lateinit var mContext :Context

    //是否第一次加载
    private var isFirst: Boolean = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext =context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,initLayout(),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onVisible()
        initVM()
        registorDefUIChange()
        initView(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    abstract fun providerVMClass(): Class<VM>
    private fun initVM() {
        providerVMClass().let {
            viewModel = ViewModelProviders.of(this).get(it)
            viewModel.let(lifecycle::addObserver)
        }
    }


    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }
    }


    open fun lazyLoadData() {}


    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(this, Observer {
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(this, Observer {
            showMsg(it)
        })
        viewModel.defUI.msgEvent.observe(this, Observer {
            handleEvent(it)
        })
    }

    open fun handleEvent(msg: AliceMessage) {}

    open fun showLoading(){}

    open fun dismissLoading(){}


    open fun showMsg(str:String){
        ToastUtils.showShort(str)
    }





}