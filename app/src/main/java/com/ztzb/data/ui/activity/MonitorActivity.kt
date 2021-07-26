package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ztzb.data.R
import com.ztzb.data.adapter.MonitorAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.MonitorBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.MonitorViewModel
import kotlinx.android.synthetic.main.activity_monitor.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MonitorActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = MonitorActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, MonitorActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var list = mutableListOf<MonitorBean>()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: MonitorViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitor)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        monitor_back_fl.setOnClickListener(this)
        monitor_setvalue_btn.setOnClickListener(this)
        monitor_report_btn.setOnClickListener(this)
        list.add(MonitorBean("运行状态", "#.##"))
        list.add(MonitorBean("当前桩号/环号", "#.##"))
        list.add(MonitorBean("推进速度", "#.##"))
        list.add(MonitorBean("皮带机转速", "#.##"))
        list.add(MonitorBean("渣温", "#.##"))
        list.add(MonitorBean("出渣量", "#.##"))
        list.add(MonitorBean("注浆量", "#.##"))
        list.add(MonitorBean("刀盘转速", "#.##"))
        list.add(MonitorBean("刀盘压力", "#.##"))
        list.add(MonitorBean("刀盘扭矩", "#.##"))
        list.add(MonitorBean("总推进力", "#.##"))
        list.add(MonitorBean("推进压力", "#.##"))
        list.add(MonitorBean("贯入度", "#.##"))
        list.add(MonitorBean("刀盘磨损量", "#.##"))
        list.add(MonitorBean("注浆压力", "#.##"))
        list.add(MonitorBean("补油压力", "#.##"))
        list.add(MonitorBean("刀具磨损压力", "#.##"))
        val monitorAdapter = MonitorAdapter(this, list)
        monitor_listview.adapter = monitorAdapter
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.monitor_back_fl -> {
                this.finish()

            }
            R.id.monitor_setvalue_btn -> {
                SetValueActivity.startActivity(this)
            }
            R.id.monitor_report_btn -> {
                ReportActivity.startActivity(this)
            }
        }
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@MonitorActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@MonitorActivity, {
                ToastManager.show(it)
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@MonitorActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}
