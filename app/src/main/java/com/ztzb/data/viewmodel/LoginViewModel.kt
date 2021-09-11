package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.repository.LoginRepository

/**
 * 作者： Zun.
 * 创建： 2021/7/20.
 * 说明：
 */
class LoginViewModel(private val repository: LoginRepository) : BaseViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName

    val loginBean = MutableLiveData<LoginBean>()

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfLogin(name: String, password: String, mac: String) {
        val params = repository.getLoginParam(name, password, mac)
        repository.requestOfLogin(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                Log.e("*****", it.toString())
                loginBean.value = it
                repository.token = it.token
                showToast("登录成功")
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

    fun getPassword(): String {
        return repository.password
    }

    fun setRemindPsw(isFlag: Boolean) {
        repository.isRemindPsw = isFlag
    }

    fun getRemindPsw(): Boolean {
        return repository.isRemindPsw
    }

}