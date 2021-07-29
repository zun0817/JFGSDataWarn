package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.WarnDeviceDetailBean
import com.ztzb.data.model.repository.WarnDeviceDetailRepository

class WarnDeviceDetailViewModel(private val repository: WarnDeviceDetailRepository) : BaseViewModel() {

    private val TAG = WarnDeviceDetailViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val warnDeviceDetailBean = MutableLiveData<WarnDeviceDetailBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfWarnDeviceDetail(warningType: String, id: Int) {
        val params = repository.getWarnDetailParam(warningType, id)
        repository.requestOfWarnDeviceDetail(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                warnDeviceDetailBean.value = it
            }, {
                showToast(it.toString())
            })

    }

}