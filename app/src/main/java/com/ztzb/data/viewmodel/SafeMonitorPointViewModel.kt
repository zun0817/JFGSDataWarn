  package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.SafeMoitorPointBean
import com.ztzb.data.model.repository.SafeMonitorPointRepository
import com.ztzb.data.util.HttpTools

class SafeMonitorPointViewModel(private val repository: SafeMonitorPointRepository) :
    BaseViewModel() {

    private val TAG = SafeMonitorPointViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val datas = MutableLiveData<SafeMoitorPointBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSafeMonitorPoint(projectSiteID: Int) {
        showLoading()
        App.instance.post(
            HttpUrl.PHP_MONITOR_POINT + "?ProjectSiteID=" + projectSiteID,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    dismissLoading()
                    Log.e("***success***", res.toString())
                    res?.let {
                        val data =
                            JSON.parseObject(res, SafeMoitorPointBean::class.java)
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