package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.ResetPasswordRepository

/**
 * 作者： Zun.
 * 创建： 2021/7/20.
 * 说明：
 */
class ResetPasswordViewModel(private val repository: ResetPasswordRepository) : BaseViewModel() {

    private val TAG = ResetPasswordViewModel::class.java.simpleName

    val smscode = MutableLiveData<String>()

    val content = MutableLiveData<String>()

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSendSMS(phone_num: String) {
        val params = repository.getSendSMSParam(phone_num)
        repository.requestOfSendSMS(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                Log.e("*****", it.toString())
                smscode.value = it
                showToast("发送成功")
            }, {
                showToast(it.toString())
            })
    }

    fun requestOfResetPassword(phone: String, password: String) {
        val params = repository.getResetPasswordParam(phone, password)
        repository.requestOfResetPassword(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                Log.e("*****", it.toString())
                content.value = it
                showToast("密码重置成功")
            }, {
                showToast(it.toString())
            })
    }

    fun setAccount(content: String) {
        repository.account = content
    }

    fun getAccount(): String {
        return repository.account
    }

    fun setPassword(content: String) {
        repository.password = content
    }

}