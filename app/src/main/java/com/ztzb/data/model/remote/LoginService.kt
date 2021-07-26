package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.LoginBean
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    /**
     * 登录
     */
    @Headers("Content-Type: application/json")
    @POST(HttpUrl.LOGIN)
    fun requestOfLogin(@Body param: RequestBody): Single<BaseResponse<LoginBean>>

}