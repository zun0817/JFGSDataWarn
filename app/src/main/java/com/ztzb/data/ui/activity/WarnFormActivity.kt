package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.ztzb.data.R
import com.ztzb.data.adapter.WarnFormAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.WarnDeviceBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.WarnFormViewModel
import kotlinx.android.synthetic.main.activity_warn_form.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WarnFormActivity : BaseMVVMActivity(), AdapterView.OnItemClickListener, View.OnClickListener {

    companion object {
        private val TAG = WarnFormActivity::class.java.simpleName
        fun startActivity(activity: Activity, warningType: String) {
            val intent = Intent()
            intent.setClass(activity, WarnFormActivity::class.java)
            intent.putExtra("warningType", warningType)
            activity.startActivity(intent)
        }
    }

    private var lists = mutableListOf<WarnDeviceBean>()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: WarnFormViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warn_form)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        warn_form_fl.setOnClickListener(this)
        warn_form_listview.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        val warningType = intent.getStringExtra("warningType")
        warningType?.let {
            when (it) {
                "mudcakeRisk" -> warn_form_title_tv.text = "泥饼风险设备列表"
                "subsideRisk" -> warn_form_title_tv.text = "沉降风险设备列表"
                "stuckshieldRisk" -> warn_form_title_tv.text = "卡盾风险设备列表"
            }
            mViewModel.requestOfWarnForm(it)
        }
    }

    override fun onClick(v: View?) {
        this.finish()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val warnFormBean = lists[position]
        WarnDeviceDetailActivity.startActivity(
            this,
            warnFormBean.warningType,
            warnFormBean.id,
            warnFormBean.intervalName,
        )
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@WarnFormActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@WarnFormActivity, {
                ToastManager.show(it)
            })
            warnDevices.observe(this@WarnFormActivity, {
                lists = it
                val warnFormAdapter = WarnFormAdapter(this@WarnFormActivity, it)
                warn_form_listview.adapter = warnFormAdapter
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@WarnFormActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}
