package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.ProjectBean
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProjectService {

    /**
     * 项目列表
     */
    @FormUrlEncoded
    @POST(HttpUrl.LOGIN)
    fun requestOfProject(): Single<BaseResponse<MutableList<ProjectBean>>>

}