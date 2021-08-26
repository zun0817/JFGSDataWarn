package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.MonitorBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RiskWarnService {

    /**
     * 掘进参数监控
     */
    @GET(HttpUrl.YIFENG_LOGIN)
    fun requestOfSafeProject(@Query("projectId") projectId: Int): Single<BaseResponse<MonitorBean>>

}