package com.ztzb.data.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ztzb.data.util.ToastManager
import com.ztzb.data.util.StatusBarUtil

import android.widget.LinearLayout




/**
 * 作者： Zun.
 * 创建： 2019/4/15.
 * 说明： Activity基类
 */
abstract class BaseMVVMActivity : BaseActivity() {

    private var viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel ?: getChildViewModel().apply { viewModel = this })
        baseViewModelObserve()
    }

    private fun baseViewModelObserve() {
        viewModel?.toastText?.observe(this, Observer {
            showToast(it)
        })
    }

    fun showToast(text: String) {
        ToastManager.show(text)
    }

    abstract fun getChildViewModel(): BaseViewModel

}
