package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.remote.SafeMonitorService
import io.reactivex.Single

class SafeMonitorRepository(private val safeMonitorService: SafeMonitorService) : BaseRepository() {

    private val TAG = SafeMonitorRepository::class.java.simpleName

    fun getSafeMonitorParam(projectId: Int): Int {
        return projectId
    }

    fun requestOfSafeMonitor(projectId: Int): Single<MonitorBean> {
        val request = safeMonitorService.requestOfSafeMonitor(projectId)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}