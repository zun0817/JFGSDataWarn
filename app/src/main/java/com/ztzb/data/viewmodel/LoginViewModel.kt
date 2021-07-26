package com.ztzb.data.viewmodel

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

    fun requestOfLogin(name: String, password: String) {
        val params = repository.getLoginParam(name, password)
        repository.requestOfLogin(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                loginBean.value = it
                repository.user_token = it.token
                showToast("登录成功")
            }, {
                showToast(it.toString())
            })

    }

}