package com.cham.alice.widget

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.cham.alice.R
import com.cham.alice.base.BaseDialogFragment
import com.cham.alice.databinding.DialogLoadingBinding

/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class LoadingView :BaseDialogFragment<DialogLoadingBinding>() {
    override fun CanceledOnTouchOutside(): Boolean =false
    companion object {
        @JvmStatic
        fun getInstance(): LoadingView {//全局访问点
            return SingletonHolder.mInstance
        }
        private lateinit var mLoadingView: LoadingView
    }
    private object SingletonHolder {//静态内部类
    val mInstance: LoadingView = LoadingView()
    }

    override fun initView(savedInstanceState: Bundle?) {
      val  animation = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 2000
        //动画的重复次数
        animation.repeatCount = -1
        //设置为true，动画转化结束后被应用
        animation.fillAfter = true
        dialogBinding.ivImage.startAnimation(animation)
    }

    override fun initLayout(): Int = R.layout.dialog_loading
}