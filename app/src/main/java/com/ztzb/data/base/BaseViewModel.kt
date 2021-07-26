package com.ztzb.data.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(), DefaultLifecycleObserver, CoroutineScope by MainScope() {
    private val TAG = BaseViewModel::class.java.simpleName

    val toastText = MutableLiveData<String>()

    val loadingDialog = MutableLiveData<Boolean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        cancel()
    }

    protected fun showToast(text: String) {
        toastText.postValue(text)
    }

    protected fun showLoading() {
        loadingDialog.postValue(true)
    }

    protected fun dismissLoading() {
        loadingDialog.postValue(false)
    }

}