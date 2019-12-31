package com.cham.alice.base
import android.os.Bundle

/**
 * Hello World
 * Date: 2019/12/2
 * Author: Cham
 */
interface IView {
    fun initLayout():Int
    fun initView(savedInstanceState: Bundle?)

}