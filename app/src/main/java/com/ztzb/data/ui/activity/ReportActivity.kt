package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ztzb.data.R
import com.ztzb.data.adapter.ReportAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.ReportBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.ReportViewModel
import kotlinx.android.synthetic.main.activity_device_info.*
import kotlinx.android.synthetic.main.activity_report.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = ReportActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            projectName: String,
            typeName: String,
            deviceName: String,
            sectionName: String,
            areaName: String
        ) {
            val intent = Intent()
            intent.setClass(activity, ReportActivity::class.java)
            intent.putExtra("deviceName", deviceName)
            intent.putExtra("typeName", typeName)
            intent.putExtra("projectName", projectName)
            intent.putExtra("sectionName", sectionName)
            intent.putExtra("areaName", areaName)
            activity.startActivity(intent)
        }
    }

    private var list = mutableListOf<ReportBean>()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: ReportViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        report_project_tv.text = "项目名称：" + intent.getStringExtra("projectName")
        report_section_tv.text = "标段名称：" + intent.getStringExtra("sectionName")
        report_area_tv.text = "区间名称：" + intent.getStringExtra("areaName")
        report_no_tv.text = "设备编号：" + intent.getStringExtra("deviceName")
        report_type_tv.text = "设备类型：" + intent.getStringExtra("typeName")

        report_back_fl.setOnClickListener(this)
        list.add(ReportBean("泥饼风险", "2021.07.16 12:30", ""))
        list.add(ReportBean("沉降风险", "2021.07.16 12:30", ""))
        list.add(ReportBean("卡盾风险", "2021.07.16 12:30", ""))
        val reportAdapter = ReportAdapter(this, list)
        report_listview.adapter = reportAdapter
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@ReportActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@ReportActivity, {
                ToastManager.show(it)
            })
        }
    }

    override fun onClick(v: View?) {
        this.finish()
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@ReportActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}