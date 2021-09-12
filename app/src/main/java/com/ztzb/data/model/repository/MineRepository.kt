package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.http.utils.Preference
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.remote.MineService
import io.reactivex.Single

class MineRepository(private val mineService: MineService) : BaseRepository() {

    private val TAG = MineRepository::class.java.simpleName

    var token: String by Preference(Preference.USER_TOKEN, "")

    fun getUserDetailParam(token: String): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        map["token"] = token
        return map
    }

    fun requestOfUserDetail(map: MutableMap<String, String>): Single<LoginBean> {
        val request = mineService.requestOfUserDetail(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

    fun requestOfLoginOut(map: MutableMap<String, String>): Single<String> {
        val request = mineService.requestOfLoginOut(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}