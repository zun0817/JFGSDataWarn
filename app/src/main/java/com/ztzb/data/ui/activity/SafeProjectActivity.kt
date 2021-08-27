package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.adapter.SafeExpandableAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.SafeProjectBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.SafeProjectViewModel
import kotlinx.android.synthetic.main.activity_safe_project.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeProjectActivity : BaseMVVMActivity(), View.OnClickListener,
    ExpandableListView.OnChildClickListener,
    ExpandableListView.OnGroupClickListener {

    companion object {
        private val TAG = SafeProjectActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SafeProjectActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private lateinit var list: MutableList<SafeProjectBean>

    private val mViewModel: SafeProjectViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_project)
        viewModelObserve()
        initView()
    }

    private fun initView() {
        back_fl.setOnClickListener(this)
        safe_exlistview.setOnChildClickListener(this)
        safe_exlistview.setOnGroupClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.requestOfSafeProject()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SafeProjectActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SafeProjectActivity, {
                ToastManager.show(it)
            })
            projects.observe(this@SafeProjectActivity, {
                list = it
                val safeExpandableAdapter = SafeExpandableAdapter(it, this@SafeProjectActivity)
                safe_exlistview.setAdapter(safeExpandableAdapter)
                it.forEachIndexed { index, _ ->
                    safe_exlistview.expandGroup(index, true)
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back_fl -> {
                this.finish()
            }
        }
    }

    override fun onChildClick(
        parent: ExpandableListView?,
        v: View?,
        groupPosition: Int,
        childPosition: Int,
        id: Long
    ): Boolean {
        val datas = list[groupPosition].children[childPosition].children
        val json = JSON.toJSONString(datas)
        SafeSectionActivity.startActivity(this, json)
        return false
    }

    override fun onGroupClick(
        parent: ExpandableListView?,
        v: View?,
        groupPosition: Int,
        id: Long
    ): Boolean {
        SafeProjectDetailActivity.startActivity(this)
        return true
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SafeProjectActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}