package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.ProjectService
import io.reactivex.Single

class ProjectRepository(private val projectService: ProjectService) : BaseRepository() {

    private val TAG = ProjectRepository::class.java.simpleName

    fun getProjectParam(name: String?, serialNum: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["ipAddress"] = serialNum
        return map
    }

    fun requestOfProject(map: MutableMap<String, String?>): Single<String> {
        val request = projectService.requestOfProject(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}