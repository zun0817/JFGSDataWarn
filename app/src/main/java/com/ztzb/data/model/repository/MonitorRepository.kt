package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.remote.MonitorService
import io.reactivex.Single

class MonitorRepository(private val monitorService: MonitorService) : BaseRepository() {

    private val TAG = MonitorRepository::class.java.simpleName

    fun getMonitorParam(name: String?, serialNum: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["ipAddress"] = serialNum
        return map
    }

    fun requestOfMonitor(map: MutableMap<String, String?>): Single<MutableList<MonitorBean>> {
        val request = monitorService.requestOfMonitor(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}