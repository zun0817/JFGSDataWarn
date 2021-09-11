package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.LoginBean
import io.reactivex.Single
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST(HttpUrl.LOGIN)
    fun requestOfLogin(@FieldMap param: MutableMap<String, String>): Single<BaseResponse<LoginBean>>

}