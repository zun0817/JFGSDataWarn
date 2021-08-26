package com.ztzb.data.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.ztzb.data.App
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.http.HttpUrl
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.model.data.SafeProjectBean
import com.ztzb.data.model.repository.SafeProjectRepository
import com.ztzb.data.util.HttpTools

class SafeProjectViewModel(private val repository: SafeProjectRepository) : BaseViewModel() {

    private val TAG = SafeProjectViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val monitorBean = MutableLiveData<MonitorBean>()

    val projects = MutableLiveData<MutableList<SafeProjectBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfSafeProject() {
//        repository.ipAddress = Constants.YIFENG_URL
//        val params = repository.getSafeProjectParam(projectId)
//        repository.requestOfSafeProject(params)
//            .polling(180, TimeUnit.SECONDS)
//            //.doOnSubscribe { showLoading() }
//            //.doAfterTerminate { dismissLoading() }
//            .disposableOnDestroy(owner)
//            .subscribe({
//                monitorBean.value = it
//            }, {
//                showToast(it.toString())
//            })
        App.instance.post(
            HttpUrl.PHP_PROJECT,
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    res?.let {
//                        val safeProjectBeanItems = mutableListOf<SafeProjectBeanItem>()
//                        val childrenY = mutableListOf<ChildrenY>()
//                        val childrenYY = mutableListOf<ChildrenYY>()
//                        val childrenYYY = mutableListOf<ChildrenYYY>()
//                        val jsonArray = JSONArray(it)
//                        for (i in 0 until jsonArray.length()){
//                            val data = jsonArray.getJSONObject(i)
//                            val expanded = data.getBoolean("expanded")
//                            val iconCls = data.getString("iconCls")
//                            val id = data.getString("id")
//                            val leaf = data.getBoolean("leaf")
//                            val level = data.getInt("level")
//                            val nodeID = data.getInt("nodeID")
//                            val text = data.getString("text")
//                            val type = data.getString("type")
//                            val arrayY = data.getJSONArray("children")
//                            for (a in 0 until arrayY.length()){
//                                val dataY = jsonArray.getJSONObject(a)
//                                val expandedY = dataY.getBoolean("expanded")
//                                val parentIDStr = dataY.getString("ParentIDStr")
//                                val iconClsY = dataY.getString("iconCls")
//                                val idY = dataY.getString("id")
//                                val leafY = dataY.getBoolean("leaf")
//                                val levelY = dataY.getInt("level")
//                                val nodeIDY = dataY.getInt("nodeID")
//                                val textY = dataY.getString("text")
//                                val typeY = dataY.getString("type")
//                                val arrayYY = dataY.getJSONArray("children")
//                                for (b in 0 until arrayYY.length()){
//                                    val dataYY = jsonArray.getJSONObject(b)
//                                    val expandedY = dataYY.getBoolean("expanded")
//                                    val parentIDStr = dataYY.getString("ParentIDStr")
//                                    val iconClsY = dataYY.getString("iconCls")
//                                    val idY = dataYY.getString("id")
//                                    val leafY = dataYY.getBoolean("leaf")
//                                    val levelY = dataYY.getInt("level")
//                                    val nodeIDY = dataYY.getInt("nodeID")
//                                    val textY = dataYY.getString("text")
//                                    val typeY = dataYY.getString("type")
//                                    val arrayYY = dataYY.getJSONArray("children")
//                                }
//                            }
//                        }
//                        val list =
//                            GsonBuilder().serializeNulls().serializeSpecialFloatingPointValues()
//                                .disableHtmlEscaping().setLenient().create()
//                                .fromJson<MutableList<SafeProjectBeanItem>>(
//                                    it,
//                                    object : TypeToken<MutableList<SafeProjectBeanItem>>() {}.type
//                                )
                        var content = it.trim()
                        if (it.trim().contains("<pre>")){
                            content = content.replace("<pre>", "")
                        }
                        if (it.trim().contains("</pre>")){
                            content = content.replace("</pre>", "")
                        }
                        val list: MutableList<SafeProjectBean> =
                            JSON.parseArray(content, SafeProjectBean::class.java)
                        projects.value = list
                    }
                }

                override fun onFailed(err: String?) {
                    Log.e("***fail***", err.toString())
                    err?.let {
                        showToast(it)
                    }
                }
            })
    }

    fun requestAutoLogin() {
        App.instance.post(
            HttpUrl.YIFENG_LOGIN,
            repository.getAutoLoginParam(),
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                    request()
                }

                override fun onFailed(err: String?) {
                    Log.e("***fail***", err.toString())
                }
            })
    }

    fun request() {
        App.instance.get(
            HttpUrl.YIFENG_PROJECT,
            repository.getProjectParam(),
            object : HttpTools.Callback {
                override fun onSuccess(res: String?) {
                    Log.e("***success***", res.toString())
                }

                override fun onFailed(err: String?) {
                    Log.e("***fail***", err.toString())
                }
            })
    }
}