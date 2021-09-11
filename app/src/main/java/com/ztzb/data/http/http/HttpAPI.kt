package com.ztzb.data.http.http

import com.ztzb.data.http.response.BaseResponse
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 作者： Zun.
 * 创建： 2018/11/6.
 * 说明： API接口入参服务管理类
 */
interface HttpAPI {

    /**
     * 设备注册
     */
    @FormUrlEncoded
    @POST(HttpUrl.LOGIN)
    fun requestOfDeviceRegister(@FieldMap param: MutableMap<String, String?>): Observable<BaseResponse<String>>

}