package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.data.SafeProjectDetailBean
import com.ztzb.data.model.repository.SafeProjectDetailRepository
import com.ztzb.data.util.HttpTools

class SafeProjectDetailViewModel(private val repository: SafeProjectDetailRepository) :
    BaseViewModel() {

    private val TAG = SafeProjectDetailViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val monitorBean = MutableLiveData<MonitorBean>()

    val datas = MutableLiveData<MutableList<SafeProjectDetailBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSafeProjectDetail() {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_PROJECT_DETAIL,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    dismissLoading()
                    res?.let {
                        val list: MutableList<SafeProjectDetailBean> =
                            JSON.parseArray(it.trim(), SafeProjectDetailBean::class.java)
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