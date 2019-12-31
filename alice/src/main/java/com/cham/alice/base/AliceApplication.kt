package com.cham.alice.base

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import com.cham.alice.utils.SpUtils


/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class AliceApplication  :Application(){


    companion object {
        lateinit var instance: AliceApplication
    }

    override fun onCreate() {
        super.onCreate()


    }
    /**
     * sp用法
     * */
    fun Sp_Do_it(){
       var us = SpUtils<String>().sp.getString("key","aaaaa")
        us ="1113321321313"
        us = "555666"
        //Sp 写法二 直接获取  通过by
        var ss by SpUtils("lalal","sssss")
        ss="ppllplp"

    }


}