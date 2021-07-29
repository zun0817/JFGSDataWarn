package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.MessageBean
import com.ztzb.data.model.repository.MessageRepository

class MessageViewModel(private val repository: MessageRepository) : BaseViewModel() {

    private val TAG = MessageViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val messageBean = MutableLiveData<MessageBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfMessage(pageNumber: Int, pageSize: Int) {
        val params = repository.getMessageParam(pageNumber, pageSize)
        repository.requestOfMessage(params)
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                messageBean.value = it
            }, {
                showToast(it.toString())
            })

    }

}