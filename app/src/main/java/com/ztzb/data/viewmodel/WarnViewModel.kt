package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.WarnRepository

class WarnViewModel(private val repository: WarnRepository) : BaseViewModel() {

    private val TAG = WarnViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfWarn(name: String, password: String) {
        val params = repository.getWarnParam(name, password)
        repository.requestOfWarn(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                showToast("登录成功")
            }, {
                showToast(it.toString())
            })

    }

}