package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.WarnDeviceBean
import com.ztzb.data.model.repository.WarnFormRepository

class WarnFormViewModel(private val repository: WarnFormRepository) : BaseViewModel() {

    private val TAG = WarnFormViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val warnDevices = MutableLiveData<MutableList<WarnDeviceBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfWarnForm(warningType: String) {
        val params = repository.getWarnFormParam(warningType)
        repository.requestOfWarnForm(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                warnDevices.value = it
            }, {
                showToast(it.toString())
            })

    }

}