package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.WarnService
import io.reactivex.Single

class WarnRepository(private val warnService: WarnService) : BaseRepository() {

    private val TAG = WarnRepository::class.java.simpleName

    fun getWarnParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfWarn(map: MutableMap<String, String?>): Single<String> {
        val request = warnService.requestOfWarn(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}