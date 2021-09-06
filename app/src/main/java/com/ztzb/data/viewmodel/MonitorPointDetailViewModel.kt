package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.MonitorPointDetailBean
import com.ztzb.data.model.repository.MonitorPointDetailRepository
import com.ztzb.data.util.HttpTools

class MonitorPointDetailViewModel(private val repository: MonitorPointDetailRepository) :
    BaseViewModel() {

    private val TAG = MonitorPointDetailViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val datas = MutableLiveData<MonitorPointDetailBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfMonitorPointDetail(SensorID: Int) {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_MONITOR_POINT_DETAIL + "?SensorID=" + SensorID,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    dismissLoading()
                    Log.e("***success***", res.toString())
                    res?.let {
                        val data =
                            JSON.parseObject(res, MonitorPointDetailBean::class.java)
                        datas.value = data
                    }
                }

                override fun onFailed(err: String?) {
                    dismissLoading()
                    Log.e("***fail***", err.toString())
                    err?.let {
                        showToast(it)
                    }
                }
            })
    }
}