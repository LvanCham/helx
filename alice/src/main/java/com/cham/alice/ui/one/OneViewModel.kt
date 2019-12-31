package com.cham.alice.ui.one

import androidx.lifecycle.MutableLiveData
import com.cham.alice.base.BaseResult
import com.cham.alice.base.BaseViewModel
import com.cham.alice.entity.HomeListEntity
import com.cham.alice.entity.RowDataEntity

/**
 * Hello World
 * Date: 2019/12/31
 * Author: Cham
 */
class OneViewModel :BaseViewModel() {

    var mHomeList  = MutableLiveData<BaseResult<RowDataEntity<HomeListEntity>>>()


    fun  getHomeList(page:Int =0){
       launchMain({
          mHomeList.value = mService.getHomePageList(page)
       })

    }
}