package com.ztzb.data.model.repository

import android.util.Log
import com.google.gson.Gson
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.http.utils.Preference
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.remote.LoginService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import java.net.URLEncoder

class LoginRepository(private val loginService: LoginService) : BaseRepository() {

    private val TAG = LoginRepository::class.java.simpleName

    var user_token: String by Preference(Preference.USER_TOKEN, "")

    fun getLoginParam(name: String?, password: String?): RequestBody {
        val map = mutableMapOf<String, String?>()
        map["userName"] = name
        map["passwd"] = URLEncoder.encode(password)
        val json = Gson().toJson(map)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        return body
    }

    fun requestOfLogin(body: RequestBody): Single<LoginBean> {
        val request = loginService.requestOfLogin(body)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}