package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProjectService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST(HttpUrl.REGISTER)
    fun requestOfProject(@FieldMap param: MutableMap<String, String?>): Single<BaseResponse<String>>

}