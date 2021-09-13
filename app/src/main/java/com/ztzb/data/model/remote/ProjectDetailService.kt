package com.ztzb.data.model.remote

import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.http.response.BaseResponse
import com.ztzb.data.model.data.ProjectDetailBean
import com.ztzb.data.model.data.SectionBean
import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProjectDetailService {

    @POST(HttpUrl.PROJECT_DETAIL)
    fun requestOfProjectDetail(): Single<BaseResponse<ProjectDetailBean>>

    @POST(HttpUrl.SECTIONS)
    fun requestOfSection(): Single<BaseResponse<MutableList<SectionBean>>>

}