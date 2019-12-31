package com.cham.alice.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.cham.alice.R
import com.cham.alice.base.BaseActivity
import com.cham.alice.databinding.ActivitySplashBinding
import com.cham.alice.ui.MainActivity
import com.cham.alice.widget.LoadingView

/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class SplashActivity :BaseActivity<SplashViewModel,ActivitySplashBinding>(),View.OnClickListener{
    override fun onClick(v: View?) {
       when(v?.id){
           R.id.btn_countdown->{
              startActivityTo(MainActivity::class.java)
           }
       }
    }

    override fun providerVMClass(): Class<SplashViewModel> =SplashViewModel::class.java

    override fun initLayout(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        binding.listener=this
        viewModel.getDailyEnglish()
        viewModel.getPoetry()
       viewModel.countDownStart(binding.btnCountdown)
        viewModel.mPoe.observe(this, Observer {
            binding.poetry =it
        })
        viewModel.mDaily.observe(this, Observer {
            binding.daily=it
        })
    }

    override fun showLoading() {
        LoadingView.getInstance().show(this)
    }

    override fun dismissLoading() {
        LoadingView.getInstance().dismissDialog()

    }

}