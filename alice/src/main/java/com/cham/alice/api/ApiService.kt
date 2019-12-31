package com.cham.alice.api




import com.cham.alice.appConstant.DAILYENGLISH
import com.cham.alice.appConstant.POETRY
import com.cham.alice.base.BaseResult
import com.cham.alice.entity.DailyEnglishEntity
import com.cham.alice.entity.HomeListEntity
import com.cham.alice.entity.PoetryEntity
import com.cham.alice.entity.RowDataEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Hello World
 * Date: 2019/7/2
 * Author: Cham
 */
interface ApiService {


    //https://api.gushi.ci/all.json
    @GET
    suspend   fun getAncientPoetry(@Url url:String =POETRY ): PoetryEntity


    //http://open.iciba.com/dsapi/1
    @GET
    suspend  fun getDailyEn(@Url url:String =DAILYENGLISH): DailyEnglishEntity


    @GET("article/list/{page}/json")
    suspend fun getHomePageList(@Path("page") page:Int ):BaseResult<RowDataEntity<HomeListEntity>>




}