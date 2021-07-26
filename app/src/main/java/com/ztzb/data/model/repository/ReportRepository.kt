package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.ReportBean
import com.ztzb.data.model.remote.ReportService
import io.reactivex.Single

class ReportRepository(private val reportService: ReportService) : BaseRepository() {

    private val TAG = ReportRepository::class.java.simpleName

    fun getReportParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfReports(map: MutableMap<String, String?>): Single<MutableList<ReportBean>> {
        val request = reportService.requestOfReports(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}