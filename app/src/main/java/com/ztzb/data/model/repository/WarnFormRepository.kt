package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.WarnFormService
import io.reactivex.Single

class WarnFormRepository(private val warnFormService: WarnFormService) : BaseRepository() {

    private val TAG = WarnFormRepository::class.java.simpleName

    fun getWarnFormParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfWarnForm(map: MutableMap<String, String?>): Single<String> {
        val request = warnFormService.requestOfWarnForm(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}