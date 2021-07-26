package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.WarnFormRepository

class WarnFormViewModel(private val repository: WarnFormRepository) : BaseViewModel() {

    private val TAG = WarnFormViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfWarnForm(name: String, password: String) {
        val params = repository.getWarnFormParam(name, password)
        repository.requestOfWarnForm(params)
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