package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.adapter.RiskWarnAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.RiskWarnBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.RiskWarnViewModel
import kotlinx.android.synthetic.main.activity_risk_warn.*
import kotlinx.android.synthetic.main.activity_safe_warn.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RiskWarnActivity : BaseMVVMActivity(),
    View.OnClickListener, AdapterView.OnItemClickListener {

    companion object {
        private val TAG = RiskWarnActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, RiskWarnActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var list: MutableList<RiskWarnBean>

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: RiskWarnViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_warn)
        viewModelObserve()
        initView()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@RiskWarnActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@RiskWarnActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@RiskWarnActivity, {
                list = it
                val riskWarnAdapter = RiskWarnAdapter(this@RiskWarnActivity, it)
                risk_warn_listview.adapter = riskWarnAdapter
            })
        }
    }

    private fun initView() {
        risk_warn_back_fl.setOnClickListener(this)
        risk_warn_listview.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        mViewModel.requestOfRiskWarn()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.risk_warn_back_fl -> {
                this.finish()
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val riskWarnBean = list[position]
        val json = JSON.toJSONString(riskWarnBean)
        RiskWarnDetailActivity.startActivity(this, json)
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@RiskWarnActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}
