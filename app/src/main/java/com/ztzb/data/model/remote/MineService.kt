package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.data.MessageBean
import io.reactivex.Single
import retrofit2.http.*

interface MineService {

    @FormUrlEncoded
    @POST(HttpUrl.USER_DETAIL)
    fun requestOfUserDetail(@FieldMap param: MutableMap<String, String>): Single<BaseResponse<LoginBean>>

    @FormUrlEncoded
    @POST(HttpUrl.LOGIN_OUT)
    fun requestOfLoginOut(@FieldMap param: MutableMap<String, String>): Single<BaseResponse<String>>

}