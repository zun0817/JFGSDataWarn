package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.http.utils.Preference
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.remote.LoginService
import io.reactivex.Single

class LoginRepository(private val loginService: LoginService) : BaseRepository() {

    private val TAG = LoginRepository::class.java.simpleName

    var token: String by Preference(Preference.USER_TOKEN, "")

    var account: String by Preference(Preference.ACCOUNT, "")

    var password: String by Preference(Preference.PASSWORD, "")

    var isRemindPsw: Boolean by Preference(Preference.IS_REMIND_PSW, true)

    fun getLoginParam(name: String, password: String, mac: String): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        map["username"] = name
        map["password"] = password
        map["mac"] = password
        return map
    }

    fun requestOfLogin(param: MutableMap<String, String>): Single<LoginBean> {
        val request = loginService.requestOfLogin(param)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}