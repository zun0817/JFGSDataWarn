package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.MonitorBean
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MonitorService {

    /**
     * 掘进参数
     */
    @FormUrlEncoded
    @POST(HttpUrl.REGISTER)
    fun requestOfMonitor(@FieldMap param: MutableMap<String, String?>): Single<BaseResponse<MutableList<MonitorBean>>>

}