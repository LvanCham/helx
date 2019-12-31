package com.cham.alice.ui.splash

import android.util.Log.e
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cham.alice.base.BaseViewModel
import com.cham.alice.entity.DailyEnglishEntity
import com.cham.alice.entity.PoetryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Hello World
 * Date: 2019/12/30
 * Author: Cham
 */
class SplashViewModel  :BaseViewModel(){


    var mPoe= MutableLiveData<PoetryEntity>()

    var mDaily=MutableLiveData<DailyEnglishEntity>()

    fun getDailyEnglish(){
        launchMain({
            mDaily.value=    mService.getDailyEn()
        })
    }

    fun getPoetry(){
        launchMain({
            mPoe.value=   mService.getAncientPoetry()
        })
    }

     fun countDownStart( btn:AppCompatButton) {
        viewModelScope.launch {
            flow {
                (5 downTo 0).forEach {
                    delay(1000)
                    emit( "$it"+"s")
                }
            }.flowOn(Dispatchers.Default)
                    .onStart {
                        // 倒计时开始 ，在这里可以让Button 禁止点击状态
                        btn.isEnabled=false
                    }
                    .onCompletion {
                        // 倒计时结束 ，在这里可以让Button 恢复点击状态
                        btn.isEnabled=true
                        btn.text = "跳过"
                    }
                    .collect {
                        // 在这里 更新LiveData 的值来显示到UI
                        btn.text = it
                    }


        }

//        (59 downTo 0)
//                .asFlow()
//                .map { "${it}s" }
//                .delayEach(1000)
//                .flowOn(Dispatchers.IO)
//                .onStart { }
//                .onCompletion { }
//                .collect { }
   }


}