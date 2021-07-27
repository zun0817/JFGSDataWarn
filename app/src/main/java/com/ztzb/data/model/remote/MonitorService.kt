package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.MonitorBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MonitorService {

    /**
     * 掘进参数监控
     */
    @GET(HttpUrl.MONITOR)
    fun requestOfMonitor(@Query("projectId") projectId: Int): Single<BaseResponse<MonitorBean>>

}