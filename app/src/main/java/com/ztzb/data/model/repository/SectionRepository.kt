package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.SectionService
import io.reactivex.Single

class SectionRepository(private val sectionService: SectionService) : BaseRepository() {

    private val TAG = SectionRepository::class.java.simpleName

    fun getSectionParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfSection(map: MutableMap<String, String?>): Single<String> {
        val request = sectionService.requestOfSection(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}