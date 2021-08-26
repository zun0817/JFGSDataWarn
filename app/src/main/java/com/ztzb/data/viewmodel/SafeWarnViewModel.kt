package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.data.SafeWarnBean
import com.ztzb.data.model.repository.SafeWarnRepository
import com.ztzb.data.util.HttpTools

class SafeWarnViewModel(private val repository: SafeWarnRepository) :
    BaseViewModel() {

    private val TAG = SafeWarnViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val monitorBean = MutableLiveData<MonitorBean>()

    val datas = MutableLiveData<MutableList<SafeWarnBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSafeWarn() {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_SAFE_WARN,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    dismissLoading()
                    res?.let {
                        val list: MutableList<SafeWarnBean> =
                            JSON.parseArray(it.trim(), SafeWarnBean::class.java)
                        datas.value = list
                    }
                }

                override fun onFailed(err: String?) {
                    Log.e("***fail***", err.toString())
                    dismissLoading()
                    err?.let {
                        showToast(it)
                    }
                }
            })
    }
}