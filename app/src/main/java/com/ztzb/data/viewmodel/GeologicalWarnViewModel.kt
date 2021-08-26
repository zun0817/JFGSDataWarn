package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.GeologicalBean
import com.ztzb.data.model.repository.RiskWarnRepository
import com.ztzb.data.util.HttpTools

class GeologicalWarnViewModel(private val repository: RiskWarnRepository) :
    BaseViewModel() {

    private val TAG = GeologicalWarnViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val datas = MutableLiveData<MutableList<GeologicalBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfGeologicalWarn() {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_GEOLOG_WARN,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    dismissLoading()
                    res?.let {
                        val list: MutableList<GeologicalBean> =
                            JSON.parseArray(it.trim(), GeologicalBean::class.java)
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