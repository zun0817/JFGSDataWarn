package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.ProjectDetailBean
import com.ztzb.data.model.data.SectionBean
import com.ztzb.data.model.remote.ProjectDetailService
import io.reactivex.Single

class ProjectDetailRepository(private val projectDetailService: ProjectDetailService) :
    BaseRepository() {

    private val TAG = ProjectDetailRepository::class.java.simpleName

    fun getSectionParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfProjectDetail(): Single<ProjectDetailBean> {
        val request = projectDetailService.requestOfProjectDetail()
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

    fun requestOfSection(): Single<MutableList<SectionBean>> {
        val request = projectDetailService.requestOfSection()
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}