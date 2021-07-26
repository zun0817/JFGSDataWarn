package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.repository.MessageRepository

class MessageViewModel(private val repository: MessageRepository) : BaseViewModel() {

    private val TAG = MessageViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val messages = MutableLiveData<MutableList<String>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

}