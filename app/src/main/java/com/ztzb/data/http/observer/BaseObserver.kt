package com.ztzb.data.http.observer

import android.content.Context
import android.widget.Toast
import com.ztzb.data.App
import com.ztzb.data.common.AppManager
import com.ztzb.data.http.exception.ResponseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseObserver<E>(private val showErrorTip: Boolean = false) : Observer<E> {
    private val wrContext: WeakReference<Context> = WeakReference(App.application)

    private lateinit var disposable: Disposable

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(data: E) {
        onSuccess(data)
    }

    override fun onError(e: Throwable) {
        val responseException: ResponseException = e as ResponseException
        if (showErrorTip) {
            Toast.makeText(
                AppManager.getInstance().currentActivity(),
                responseException.getErrorMessage().toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
        onError(responseException)
    }

    override fun onComplete() {

    }

    fun getDisposable() = disposable

    abstract fun onSuccess(data: E)

    abstract fun onError(e: ResponseException)
}