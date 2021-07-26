package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.repository.ReportRepository

class ReportViewModel(private val repository: ReportRepository) : BaseViewModel() {

    private val TAG = ReportViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfReports(name: String, password: String) {
        val params = repository.getReportParam(name, password)
        repository.requestOfReports(params)
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