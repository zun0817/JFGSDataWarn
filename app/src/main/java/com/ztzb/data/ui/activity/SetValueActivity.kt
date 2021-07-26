package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.SetValueViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetValueActivity : BaseMVVMActivity() {

    companion object {
        private val TAG = SetValueActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SetValueActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: SetValueViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_value)
        viewModelObserve()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SetValueActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SetValueActivity, {
                ToastManager.show(it)
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SetValueActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}