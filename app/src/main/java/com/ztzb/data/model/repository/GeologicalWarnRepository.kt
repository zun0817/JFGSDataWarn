package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.common.Constants
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.http.utils.Preference
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.remote.GeologicalWarnService
import io.reactivex.Single

class GeologicalWarnRepository(private val geologicalWarnService: GeologicalWarnService) :
    BaseRepository() {

    private val TAG = GeologicalWarnRepository::class.java.simpleName

    var ipAddress: String by Preference(Preference.HTTP_URL, Constants.BASE_URL)

    fun getSafeProjectParam(projectId: Int): Int {
        return projectId
    }

    fun getAutoLoginParam(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["username"] = "DataApi"
        map["password"] = "d84902fc37260347311427532b0feeb0191c8992"
        return map
    }

    fun getProjectParam(): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["ProjectID"] = 1
        map["flag"] = 0
        map["expanded0"] = 0
        map["expanded1"] = 0
        map["expanded1"] = 0
        map["expanded2"] = 0
        map["expanded3"] = 0
        map["level"] = 5
        return map
    }

    fun requestOfSafeProject(projectId: Int): Single<MonitorBean> {
        val request = geologicalWarnService.requestOfSafeProject(projectId)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}