package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ztzb.data.R
import com.ztzb.data.adapter.MonitorPointAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.SafeMonitorPointViewModel
import kotlinx.android.synthetic.main.activity_safe_monitor_point.*
import kotlinx.android.synthetic.main.activity_safe_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeMonitorPointActivity : BaseMVVMActivity() {

    companion object {
        private val TAG = SafeMonitorPointActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            projectSiteID: Int
        ) {
            val intent = Intent()
            intent.setClass(activity, SafeMonitorPointActivity::class.java)
            intent.putExtra("projectSiteID", projectSiteID)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: SafeMonitorPointViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_monitor_point)
        viewModelObserve()
        initView()
    }

    private fun initView(){
        val projectSiteID = intent.getIntExtra("projectSiteID", 0)
        mViewModel.requestOfSafeMonitorPoint(projectSiteID)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SafeMonitorPointActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SafeMonitorPointActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@SafeMonitorPointActivity, { bean ->
                bean.data.takeIf { it.size > 0 }?.apply {
                    val monitorPointAdapter = MonitorPointAdapter(this@SafeMonitorPointActivity, this)
                    point_listview.adapter = monitorPointAdapter
                }
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SafeMonitorPointActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}