package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.adapter.SafeWarnAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.SafeWarnBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.SafeWarnViewModel
import kotlinx.android.synthetic.main.activity_safe_project_detail.*
import kotlinx.android.synthetic.main.activity_safe_warn.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeWarnActivity : BaseMVVMActivity(), View.OnClickListener, AdapterView.OnItemClickListener {

    companion object {
        private val TAG = SafeWarnActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SafeWarnActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var list: MutableList<SafeWarnBean>

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: SafeWarnViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_warn)
        viewModelObserve()
        initView()
    }

    private fun initView() {
        safe_warn_back_fl.setOnClickListener(this)
        safe_warn_listview.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        mViewModel.requestOfSafeWarn()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.safe_warn_back_fl -> {
                this.finish()
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val safeWarnBean = list[position]
        val json = JSON.toJSONString(safeWarnBean)
        SafeWarnDetailActivity.startActivity(this, json)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SafeWarnActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SafeWarnActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@SafeWarnActivity, {
                list = it
                val safeWarnAdapter = SafeWarnAdapter(this@SafeWarnActivity, it)
                safe_warn_listview.adapter = safeWarnAdapter
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SafeWarnActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}