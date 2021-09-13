package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.OverallProgressService
import io.reactivex.Single

class OverallProgressRepository(private val overallProgressService: OverallProgressService) : BaseRepository() {

    private val TAG = OverallProgressRepository::class.java.simpleName

    fun getValueParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfValue(map: MutableMap<String, String?>): Single<String> {
        val request = overallProgressService.requestOfValue(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}