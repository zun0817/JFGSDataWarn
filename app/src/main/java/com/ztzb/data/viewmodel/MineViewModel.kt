package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.LoginBean
import com.ztzb.data.model.repository.MineRepository

class MineViewModel(private val repository: MineRepository) : BaseViewModel() {

    private val TAG = MineViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val loginBean = MutableLiveData<LoginBean>()

    val data = MutableLiveData<String>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfUserDetail(token: String) {
        val params = repository.getUserDetailParam(token)
        repository.requestOfUserDetail(params)
            .disposableOnDestroy(owner)
            .subscribe({
                loginBean.value = it
            }, {
                showToast(it.toString())
            })
    }

    fun requestOfLoginOut(token: String) {
        val params = repository.getUserDetailParam(token)
        repository.requestOfLoginOut(params)
            .disposableOnDestroy(owner)
            .subscribe({
                data.value = it
                showToast("退出登录")
            }, {
                showToast(it.toString())
            })
    }

    fun getToken(): String {
        return repository.token
    }

}