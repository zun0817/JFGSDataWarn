package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.remote.LoginService
import io.reactivex.Single

class LoginRepository(private val loginService: LoginService) : BaseRepository() {

    private val TAG = LoginRepository::class.java.simpleName

    fun getLoginParam(name: String?, password: String?): MutableMap<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["name"] = name
        map["password"] = password
        return map
    }

    fun requestOfLogin(map: MutableMap<String, String?>): Single<String> {
        val request = loginService.requestOfLogin(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}