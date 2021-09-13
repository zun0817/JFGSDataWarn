package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.OverallProgressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverallProgressActivity : BaseMVVMActivity() {

    companion object {
        private val TAG = OverallProgressActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, OverallProgressActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: OverallProgressViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overall_progress)
        viewModelObserve()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@OverallProgressActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@OverallProgressActivity, {
                ToastManager.show(it)
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@OverallProgressActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}