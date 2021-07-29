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
import kotlinx.android.synthetic.main.activity_report.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = ReportActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, ReportActivity::class.java)
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