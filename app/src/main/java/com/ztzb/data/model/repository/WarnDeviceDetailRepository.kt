package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.WarnDeviceDetailBean
import com.ztzb.data.model.remote.WarnDeviceDetailService
import io.reactivex.Single

class WarnDeviceDetailRepository(private val warnDeviceDetailService: WarnDeviceDetailService) :
    BaseRepository() {

    private val TAG = WarnDeviceDetailRepository::class.java.simpleName

    fun getWarnDetailParam(warningType: String, id: Int): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["warningType"] = warningType
        map["id"] = id
        return map
    }

    fun requestOfWarnDeviceDetail(map: MutableMap<String, Any>): Single<WarnDeviceDetailBean> {
        val request = warnDeviceDetailService.requestOfWarnDeviceDetail(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}