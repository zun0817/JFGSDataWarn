package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.SetValueRepository

class SetValueViewModel(private val repository: SetValueRepository) : BaseViewModel() {

    private val TAG = SetValueViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val projects = MutableLiveData<MutableList<String>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfValue(name: String, password: String) {
        val params = repository.getValueParam(name, password)
        repository.requestOfValue(params)
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