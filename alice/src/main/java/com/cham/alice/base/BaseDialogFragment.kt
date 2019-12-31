package com.cham.alice.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log.e
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
abstract class BaseDialogFragment<T : ViewDataBinding> :DialogFragment(),LifecycleObserver ,IView{
    private val DEFAULT_DIMAMOUNT = 0.3f
    lateinit var dialogBinding:T
    override fun onStart() {
        super.onStart()
        e("BaseDialogFragment","onStart")
        val window = dialog?.window
        if (window != null) {
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗口宽 高
            val mLayoutParams = window.attributes
            if (getDialogWidth() > 0) {
                mLayoutParams.width = -1
            } else {
                mLayoutParams.width = getDialogWidth()
            }
            if (getDialogHeight() > 0) {
                mLayoutParams.height = -1
            } else {
                mLayoutParams.height = getDialogHeight()
            }
            //透明度
            mLayoutParams.dimAmount = getDimAmount()
            //位置
            mLayoutParams.gravity = getGravity()
            window.attributes = mLayoutParams
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //去除Dialog默认头部
        val dialog = dialog
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        isCancelable = isCancelable
        dialog?.setCanceledOnTouchOutside(CanceledOnTouchOutside())
        if (dialog?.window != null && getDialogAnimationRes() > 0) {
            dialog.window!!.setWindowAnimations(getDialogAnimationRes())
        }
        initView(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogBinding= DataBindingUtil.inflate(inflater,initLayout(),container,false)
        return dialogBinding.root
    }
    //默认弹窗位置为中心
    fun getGravity(): Int {
        return Gravity.CENTER
    }

    //默认宽高为包裹内容
    fun getDialogHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    fun getDialogWidth(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }
    //默认透明度为0.2
    fun getDimAmount(): Float {
        return DEFAULT_DIMAMOUNT
    }
    /**
     * 获取弹窗显示动画,子类实现
     */
     fun getDialogAnimationRes(): Int {
        return 0
    }


   abstract fun CanceledOnTouchOutside( ):Boolean


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun oCCC(){

        e("BaseDialogFragment","onCCCC")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onDDD(){
        dialog?.dismiss()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }
    fun show(activity :AppCompatActivity) {
        val ss = activity.supportFragmentManager
        activity.lifecycle.addObserver(this)
        //show(fragmentManager, getFragmentTag());
        //主要是为了解决Can not perform this action after onSaveInstanceState异常
        //发生场景：Activity即将被销毁，再给它添加Fragment就会出错。
        val ft = ss.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()

    }
    /**
     * 一定要销毁dialog，设置为null，防止内存泄漏
     * GitHub地址：https://github.com/yangchong211
     * 如果可以，欢迎star
     */
    fun dismissDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }
}