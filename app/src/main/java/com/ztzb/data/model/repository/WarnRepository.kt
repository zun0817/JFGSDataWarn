package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.WarnBean
import com.ztzb.data.model.remote.WarnService
import io.reactivex.Single

class WarnRepository(private val warnService: WarnService) : BaseRepository() {

    private val TAG = WarnRepository::class.java.simpleName

    fun getWarnParam(userId: String?, orgId: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["userId"] = userId
        map["orgId"] = orgId
        return map
    }

    fun requestOfWarn(): Single<WarnBean> {
        val request = warnService.requestOfWarn()
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}