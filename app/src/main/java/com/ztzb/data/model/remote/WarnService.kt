package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.WarnBean
import io.reactivex.Single
import retrofit2.http.GET

interface WarnService {

    /**
     * 风险预警设备数量统计
     */
    @GET(HttpUrl.WARN_COUNT)
    fun requestOfWarn(): Single<BaseResponse<WarnBean>>

}