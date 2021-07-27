package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.http.rxjava.polling
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.repository.MonitorRepository
import java.util.concurrent.TimeUnit

class MonitorViewModel(private val repository: MonitorRepository) : BaseViewModel() {

    private val TAG = MonitorViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val monitorBean = MutableLiveData<MonitorBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfMonitor(projectId: Int) {
        val params = repository.getMonitorParam(projectId)
        repository.requestOfMonitor(params)
            .polling(180, TimeUnit.SECONDS)
            //.doOnSubscribe { showLoading() }
            //.doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                monitorBean.value = it
            }, {
                showToast(it.toString())
            })

    }
}