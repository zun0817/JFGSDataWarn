package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.MonitorRepository

class MonitorViewModel(private val repository: MonitorRepository) : BaseViewModel() {

    private val TAG = MonitorViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val datas = MutableLiveData<MutableList<String>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfMonitor(name: String, password: String) {
        val params = repository.getMonitorParam(name, password)
        repository.requestOfMonitor(params)
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