package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.remote.MonitorService
import io.reactivex.Single

class MonitorRepository(private val monitorService: MonitorService) : BaseRepository() {

    private val TAG = MonitorRepository::class.java.simpleName

    fun getMonitorParam(projectId: Int): Int {
        return projectId
    }

    fun requestOfMonitor(projectId: Int): Single<MonitorBean> {
        val request = monitorService.requestOfMonitor(projectId)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}