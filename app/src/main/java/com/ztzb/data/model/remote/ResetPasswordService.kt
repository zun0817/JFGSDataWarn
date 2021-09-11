package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.LoginBean
import io.reactivex.Single
import retrofit2.http.*

interface ResetPasswordService {

    @FormUrlEncoded
    @POST(HttpUrl.SEND_SMS)
    fun requestOfSendSMS(@FieldMap param: MutableMap<String, String>): Single<BaseResponse<String>>

    @FormUrlEncoded
    @POST(HttpUrl.RESET_PASSWORD)
    fun requestOfResetPassword(@FieldMap param: MutableMap<String, String>): Single<BaseResponse<String>>

}