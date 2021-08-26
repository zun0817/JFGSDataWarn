package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.http.rxjava.polling
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.repository.SafeMonitorRepository
import java.util.concurrent.TimeUnit

class SafeSectionViewModel(private val repository: SafeMonitorRepository) : BaseViewModel() {

    private val TAG = SafeSectionViewModel::class.java.simpleName

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
        val params = repository.getSafeMonitorParam(projectId)
        repository.requestOfSafeMonitor(params)
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