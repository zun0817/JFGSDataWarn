package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.RiskWarnBean
import com.ztzb.data.model.repository.RiskWarnRepository
import com.ztzb.data.util.HttpTools

class RiskWarnViewModel(private val repository: RiskWarnRepository) :
    BaseViewModel() {

    private val TAG = RiskWarnViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val datas = MutableLiveData<MutableList<RiskWarnBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfRiskWarn() {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_RISK_WARN,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    dismissLoading()
                    res?.let {
                        val list: MutableList<RiskWarnBean> =
                            JSON.parseArray(it.trim(), RiskWarnBean::class.java)
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