package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.repository.MainRepository

/**
 * 作者： Zun.
 * 创建： 2021/7/20.
 * 说明：
 */
class MainViewModel(private val repository: MainRepository) : BaseViewModel() {

    private val TAG = MainViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

}