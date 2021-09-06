package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ztzb.data.R
import com.ztzb.data.adapter.MonitorPointDetailAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.MonitorPointDetailViewModel
import kotlinx.android.synthetic.main.activity_monitor_point_detail.*
import kotlinx.android.synthetic.main.activity_safe_monitor_point.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonitorPointDetailActivity : BaseMVVMActivity() {

    companion object {
        private val TAG = MonitorPointDetailActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            sensorID: Int
        ) {
            val intent = Intent()
            intent.setClass(activity, MonitorPointDetailActivity::class.java)
            intent.putExtra("SensorID", sensorID)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: MonitorPointDetailViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor_point_detail)
        viewModelObserve()
        initView()
    }

    private fun initView(){
        val sensorID = intent.getIntExtra("SensorID", 0)
        mViewModel.requestOfMonitorPointDetail(sensorID)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@MonitorPointDetailActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@MonitorPointDetailActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@MonitorPointDetailActivity, { bean ->
                bean.data.takeIf { it.size > 0 }?.apply {
                    val monitorPointDetailAdapter = MonitorPointDetailAdapter(this@MonitorPointDetailActivity, this)
                    point_detail_listview.adapter = monitorPointDetailAdapter
                }
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@MonitorPointDetailActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}