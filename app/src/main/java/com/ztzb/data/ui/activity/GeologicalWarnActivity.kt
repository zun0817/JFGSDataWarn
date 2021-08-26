package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.adapter.GeologicalWarnAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.GeologicalBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.GeologicalWarnViewModel
import kotlinx.android.synthetic.main.activity_geological_warn.*
import kotlinx.android.synthetic.main.activity_risk_warn.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeologicalWarnActivity : BaseMVVMActivity(), View.OnClickListener,
    AdapterView.OnItemClickListener {

    companion object {
        private val TAG = GeologicalWarnActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, GeologicalWarnActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var list: MutableList<GeologicalBean>

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: GeologicalWarnViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geological_warn)
        viewModelObserve()
        initView()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@GeologicalWarnActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@GeologicalWarnActivity, {
                ToastManager.show(it)
            })
            datas.observe(this@GeologicalWarnActivity, {
                list = it
                val geologicalWarnAdapter = GeologicalWarnAdapter(this@GeologicalWarnActivity, it)
                geological_warn_listview.adapter = geologicalWarnAdapter
            })
        }
    }

    private fun initView() {
        geological_warn_back_fl.setOnClickListener(this)
        geological_warn_listview.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        mViewModel.requestOfGeologicalWarn()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.geological_warn_back_fl -> {
                this.finish()
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val geologicalBean = list[position]
        val json = JSON.toJSONString(geologicalBean)
        GeologicalDetailActivity.startActivity(this, json)
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@GeologicalWarnActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}