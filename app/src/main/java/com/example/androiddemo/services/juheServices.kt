package com.example.androiddemo.services

import com.example.androiddemo.base.*
import com.example.androiddemo.bean.juheBean.*
import com.example.androiddemo.utils.DataUtils
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.*

interface juheServices {

    @GET("todayOnhistory/queryEvent.php")
    fun acquireHistoryTodayD(@Query("date")data : String = DataUtils.acquireDate(), @Query("key")key : String = HISTORYTODAYKEY) : Observable<RequestResult<ResultDate>>

    @POST("todayOnhistory/queryDetail.php")
    fun acquireHistoryTodayI(@Query("e_id") e_id : String, @Query("key") key : String = HISTORYTODAYKEY) : Observable<RequestResult<ResultId>>

    @POST(NBAGANME)
    fun acquireNBAGameI(@Query("key") key : String = NBAKEY) : Observable<RequestResult<NbaResult>>

}