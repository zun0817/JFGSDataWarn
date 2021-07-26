package com.ztzb.data.http.observer

import android.content.Context
import com.ztzb.data.view.LoadingDialog
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class LoadingObserver<E>(
    context: Context,
    showLoading: Boolean = true,
    showErrorTip: Boolean = false,
    showLoadingContent: String? = ""
) : BaseObserver<E>(showErrorTip) {

    private val wrContext: WeakReference<Context> = WeakReference(context)

    private var loadingDialog: LoadingDialog? = null

    private var loadingContent: String? = ""

    init {
        this.loadingContent = showLoadingContent
        if (showLoading)
            loadingDialog = initLoading()
    }

    override fun onSubscribe(d: Disposable) {
        showLoading()
        super.onSubscribe(d)
    }

    override fun onError(e: Throwable) {
        dismissLoading()
        super.onError(e)
    }

    override fun onNext(data: E) {
        dismissLoading()
        super.onNext(data)
    }

    /**
     * 初始化loading
     */
    private fun initLoading(): LoadingDialog {
        return LoadingDialog()
    }

    /**
     * 显示loading
     */
    private fun showLoading() {
        loadingDialog?.showLoading(wrContext.get(), loadingContent)
    }

    /**
     * 取消loading
     */
    private fun dismissLoading() {
        loadingDialog?.dismiss()
        wrContext.clear()
    }
}