package com.cham.alice.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.cham.alice.base.AliceApplication
import kotlin.reflect.KProperty

/**
 * Hello World
 * Date: 2019/5/16
 * Author: Cham
 *SharedPreferences 工具
 */
class SpUtils<T> {

    private  var default: T? = null
    private  var name :String?=null

    constructor()

    constructor( Key :String,  default1: T){
        name =Key
        default=default1
    }

    val sp: SharedPreferences by lazy {
        AliceApplication.instance.applicationContext.getSharedPreferences("Alice", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(name!!, default!!)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(name!!, value)
    }


    @SuppressLint("CommitPrefEdits")
    private fun putSharedPreferences(name: String, value: T) = with(sp.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("SharedPreferences can't be save this type")
        }.apply()
    }


    @Suppress("UNCHECKED_CAST")
    private fun getSharedPreferences(name: String, default: T): T = with(sp) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("SharedPreferences can't be get this type")
        }
        return res as T
    }

    /**
     * 删除全部数据
     * */
    private fun clearPreference() :Boolean=sp.edit().clear().commit()

    /**
     * 根据key删除存储数据
     */
    private fun clearPreference(key : String){
        sp.edit().remove(key).apply()
    }
}