package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.SectionRepository

class SectionViewModel(private val repository: SectionRepository) : BaseViewModel() {

    private val TAG = SectionViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val sections = MutableLiveData<MutableList<String>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSection(name: String, password: String) {
        val params = repository.getSectionParam(name, password)
        repository.requestOfSection(params)
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