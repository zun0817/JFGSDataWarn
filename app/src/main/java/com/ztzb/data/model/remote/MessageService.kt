package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.MessageBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MessageService {

    /**
     * 推送消息列表
     */
    @GET(HttpUrl.MESSAGES)
    fun requestOfMessage(@QueryMap param:MutableMap<String, Any>): Single<BaseResponse<MessageBean>>

}