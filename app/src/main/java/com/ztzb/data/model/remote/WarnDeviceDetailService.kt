package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.WarnDeviceDetailBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WarnDeviceDetailService {

    /**
     * 某种风险类型的所有的设备及信息
     */
    @GET(HttpUrl.WARN_DEVICE)
    fun requestOfWarnDeviceDetail(@QueryMap param:MutableMap<String, Any>): Single<BaseResponse<WarnDeviceDetailBean>>

}