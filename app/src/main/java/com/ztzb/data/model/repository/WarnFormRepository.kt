package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.WarnDeviceBean
import com.ztzb.data.model.remote.WarnFormService
import io.reactivex.Single

class WarnFormRepository(private val warnFormService: WarnFormService) : BaseRepository() {

    private val TAG = WarnFormRepository::class.java.simpleName

    fun getWarnFormParam(warningType: String): String {
        return warningType
    }

    fun requestOfWarnForm(warningType: String): Single<MutableList<WarnDeviceBean>> {
        val request = warnFormService.requestOfWarnForm(warningType)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}