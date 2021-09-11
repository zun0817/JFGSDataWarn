package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.http.utils.Preference
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.remote.ResetPasswordService
import io.reactivex.Single

class ResetPasswordRepository(private val resetPasswordService: ResetPasswordService) :
    BaseRepository() {

    private val TAG = ResetPasswordRepository::class.java.simpleName

    var account: String by Preference(Preference.ACCOUNT, "")

    var password: String by Preference(Preference.PASSWORD, "")

    fun getSendSMSParam(phone_num: String): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        map["phone"] = phone_num
        return map
    }

    fun getResetPasswordParam(phone: String, password: String): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        map["phone"] = phone
        map["password"] = password
        return map
    }

    fun requestOfSendSMS(param: MutableMap<String, String>): Single<String> {
        val request = resetPasswordService.requestOfSendSMS(param)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

    fun requestOfResetPassword(param: MutableMap<String, String>): Single<String> {
        val request = resetPasswordService.requestOfResetPassword(param)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}