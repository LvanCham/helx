package com.cham.alice.base

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.cham.alice.base.event.AliceMessage
import com.tbruyelle.rxpermissions2.RxPermissions
import java.io.Serializable



/**
 * Hello World
 * Date: 2019/12/2
 * Author: Cham
 */
abstract class BaseActivity <VM : BaseViewModel,DB : ViewDataBinding>:AppCompatActivity() , IView {

    open val TAG =this.javaClass.simpleName
    open lateinit var binding :DB
    open lateinit var viewModel :VM

    open val  rxPermissions: RxPermissions by lazy   {
        RxPermissions(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        //true 暗色 false 白色
        BarUtils.setStatusBarLightMode(this, false)

        binding =DataBindingUtil.setContentView(this,initLayout())
        binding.lifecycleOwner = this
        initVM()
        registorDefUIChange()
        initView(savedInstanceState)
    }



    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptWidth(super.getResources(), 375)
    }


    abstract fun providerVMClass(): Class<VM>
    private fun initVM() {
        providerVMClass().let {
            viewModel = ViewModelProviders.of(this).get(it)
            viewModel.let(lifecycle::addObserver)
        }
    }


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
    open fun handleEvent(msg: AliceMessage) {
    }

    open fun showLoading(){}

    open fun dismissLoading(){}

    open fun showMsg(str:String){
        ToastUtils.showShort(str)
    }
    open fun startActivityTo(z: Class<*>) {
        startActivity(Intent(this, z))
    }

    open fun startActivityTo(z: Class<*>, name: String, value: Boolean) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }
    open fun startActivityTo(z: Class<*>, name: String, value: String) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }
    open fun startActivityTo(z: Class<*>, name: String, value: Serializable) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }
    open fun startActivityTo(z: Class<*>, name: String, value: Bundle) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }

    fun backgroundAlpha(context: Activity, bgAlpha: Float) {
        val lp = context.window.attributes
        lp.alpha = bgAlpha
        context.window
            .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        context.window.attributes = lp
    }
}