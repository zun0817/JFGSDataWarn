package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ztzb.data.R
import com.ztzb.data.adapter.SafeDetailAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.SafeProjectDetailViewModel
import kotlinx.android.synthetic.main.activity_safe_project_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeProjectDetailActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = SafeProjectDetailActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SafeProjectDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: SafeProjectDetailViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_project_detail)
        viewModelObserve()
        initView()
    }

    private fun initView() {
        back_fl.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.requestOfSafeProjectDetail()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back_fl -> {
                this.finish()
            }
        }
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SafeProjectDetailActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SafeProjectDetailActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@SafeProjectDetailActivity, {
                val safeDetailAdapter = SafeDetailAdapter(this@SafeProjectDetailActivity, it)
                safe_detail_listview.adapter = safeDetailAdapter
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SafeProjectDetailActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}