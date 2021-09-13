package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.ztzb.data.R
import com.ztzb.data.adapter.ExpandableAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.SectionBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.util.ViewTouchUtil
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.ProjectDetailViewModel
import kotlinx.android.synthetic.main.activity_project_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProjectDetailActivity : BaseMVVMActivity(),
    View.OnClickListener, ExpandableListView.OnChildClickListener {

    companion object {
        private val TAG = ProjectDetailActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, ProjectDetailActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var lists = mutableListOf<SectionBean>()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: ProjectDetailViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        project_detail_back_img.setOnClickListener(this)
        kanban_progress_tv.setOnClickListener(this)
        kanban_efficient_tv.setOnClickListener(this)
        kanban_warn_tv.setOnClickListener(this)
        kanban_police_tv.setOnClickListener(this)
        kanban_safe_tv.setOnClickListener(this)
        project_detail_exlistview.setOnChildClickListener(this)
        ViewTouchUtil.expandViewTouchDelegate(project_detail_back_img)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@ProjectDetailActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@ProjectDetailActivity, {
                ToastManager.show(it)
            })
            projectDetailBean.observe(this@ProjectDetailActivity, {
                project_detail_section_count_tv.text = it.section_num.toString()
                project_detail_digging_tv.text = it.tunneling_total
                project_detail_device_count_tv.text = it.equipment_num.toString()
            })
            sections.observe(this@ProjectDetailActivity, {
                lists = it
                val expandableAdapter = ExpandableAdapter(it, this@ProjectDetailActivity)
                project_detail_exlistview.setAdapter(expandableAdapter)
            })
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.kanban_progress_tv -> {
                showToast("功能开发中，敬请期待")
            }
            R.id.kanban_efficient_tv -> {
                showToast("功能开发中，敬请期待")
            }
            R.id.kanban_warn_tv -> {
                showToast("功能开发中，敬请期待")
            }
            R.id.kanban_police_tv -> {
                showToast("功能开发中，敬请期待")
            }
            R.id.kanban_safe_tv -> {
                SafeProjectActivity.startActivity(this)
            }
            R.id.project_detail_back_img -> {
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
//        val children = lists[groupPosition]
//        val childrenX = lists[groupPosition].children[childPosition]
//        intent.getStringExtra("projectName")?.let {
//            DeviceInfoActivity.startActivity(
//                this,
//                childrenX.groupName,
//                childrenX.typeName,
//                it,
//                children.projectName,
//                childrenX.projectName,
//                childrenX.id
//            )
//        }
        return false
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@ProjectDetailActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}