package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ExpandableListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ztzb.data.R
import com.ztzb.data.adapter.ExpandableAdapter
import com.ztzb.data.adapter.SectionAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.Children
import com.ztzb.data.model.data.ChildrenX
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.view.SectionDialog
import com.ztzb.data.viewmodel.SectionViewModel
import kotlinx.android.synthetic.main.activity_section.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SectionActivity : BaseMVVMActivity(),
    AdapterView.OnItemClickListener, View.OnClickListener, ExpandableListView.OnChildClickListener {

    companion object {
        private val TAG = SectionActivity::class.java.simpleName
        fun startActivity(activity: Activity, sections: String) {
            val intent = Intent()
            intent.putExtra("section", sections)
            intent.setClass(activity, SectionActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var lists = mutableListOf<Children>()

    private var dialogList = mutableListOf<ChildrenX>()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: SectionViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section)
        viewModelObserve()
        initView()
    }

    private fun initView() {
        dialogList = mutableListOf()
        if (intent.hasExtra("section")) {
            lists = Gson().fromJson(
                intent.getStringExtra("section"),
                object : TypeToken<List<Children>>() {}.type
            )
            val sectionAdapter = SectionAdapter(this, lists)
            section_listview.adapter = sectionAdapter
            val expandableAdapter = ExpandableAdapter(lists, this)
            section_exlistview.setAdapter(expandableAdapter)
        }
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//        lists.add("水资源配置工程A7标")
//
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")
//        dialogList.add("粤海26 GZ13-1#~Z13#左线")

        section_listview.onItemClickListener = this
        back_fl.setOnClickListener(this)
        section_exlistview.setOnChildClickListener(this)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@SectionActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@SectionActivity, {
                ToastManager.show(it)
            })
            sections.observe(this@SectionActivity, {
            })
        }
    }

    override fun onClick(v: View?) {
        this.finish()
    }

    override fun onChildClick(
        parent: ExpandableListView?,
        v: View?,
        groupPosition: Int,
        childPosition: Int,
        id: Long
    ): Boolean {
        val childrenX = lists[groupPosition].children[childPosition]
        MonitorActivity.startActivity(
            this,
            childrenX.projectName,
            childrenX.typeName,
            childrenX.id
        )
        return false
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lists.takeIf { it.size > 0 }?.apply {
            lists[position].children.takeIf { it.isNotEmpty() }?.apply {
                val sectionDialog = SectionDialog(
                    this@SectionActivity,
                    this as MutableList<ChildrenX>
                )
                sectionDialog.setCancelable(false)
                sectionDialog.setCanceledOnTouchOutside(false)
                sectionDialog.show()
            } ?: apply {
                showToast("暂无数据")
            }
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@SectionActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}