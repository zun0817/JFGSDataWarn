package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.WarnBean
import com.ztzb.data.model.repository.WarnRepository

class WarnViewModel(private val repository: WarnRepository) : BaseViewModel() {

    private val TAG = WarnViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val warnBean = MutableLiveData<WarnBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfWarn() {
        repository.requestOfWarn()
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                warnBean.value = it
            }, {
                showToast(it.toString())
            })

    }

}