package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.adapter.SectionExpandableAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.SafeProjectBean
import com.ztzb.data.util.ToastManager
import com.ztzb.data.viewmodel.SafeSectionViewModel
import kotlinx.android.synthetic.main.activity_safe_section.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeSectionActivity : BaseMVVMActivity(), View.OnClickListener,
    ExpandableListView.OnChildClickListener,
    ExpandableListView.OnGroupClickListener,
    ExpandableListView.OnGroupExpandListener {

    companion object {
        private val TAG = SafeSectionActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            content: String
        ) {
            val intent = Intent()
            intent.setClass(activity, SafeSectionActivity::class.java)
            intent.putExtra("data", content)
            activity.startActivity(intent)
        }
    }

    private lateinit var list: MutableList<SafeProjectBean.ChildrenDTOXX.ChildrenDTOX>

    private val mViewModel: SafeSectionViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_section)
        initView()
    }

    private fun initView() {
        back_fl.setOnClickListener(this)
        safe_section_exlistview.setOnChildClickListener(this)
        safe_section_exlistview.setOnGroupClickListener(this)
        safe_section_exlistview.setOnGroupExpandListener(this)
        val content = intent.getStringExtra("data")
        list = JSON.parseArray(content, SafeProjectBean.ChildrenDTOXX.ChildrenDTOX::class.java)
        val sectionExpandableAdapter = SectionExpandableAdapter(list, this)
        safe_section_exlistview.setAdapter(sectionExpandableAdapter)
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

        return false
    }

    override fun onGroupClick(
        parent: ExpandableListView?,
        v: View?,
        groupPosition: Int,
        id: Long
    ): Boolean {
        if (list[groupPosition].children == null) {
            ToastManager.show("功能开发中，敬请期待")
        }
        return true
    }

    override fun onGroupExpand(groupPosition: Int) {
        list.forEachIndexed { index, _ ->
            if (groupPosition != index) {
                safe_section_exlistview.collapseGroup(index)
            }
        }
    }
}