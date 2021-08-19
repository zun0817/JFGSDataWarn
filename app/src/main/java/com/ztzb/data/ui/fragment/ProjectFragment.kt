package com.ztzb.data.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.google.gson.Gson
import com.ztzb.data.R
import com.ztzb.data.adapter.ProjectAdapter
import com.ztzb.data.base.BaseMVVMFragment
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.ProjectBean
import com.ztzb.data.ui.activity.SectionActivity
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectFragment : BaseMVVMFragment(), AdapterView.OnItemClickListener {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    private var lists = mutableListOf<ProjectBean>()

    private lateinit var project_listview: ListView

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: ProjectViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.project_fragment, container, false)
        initView(view)
        viewModelObserve()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("*******Projectuser", isVisibleToUser.toString())
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("*******Projecthidden", hidden.toString())
    }

    private fun initView(view: View) {
        project_listview = view.findViewById(R.id.project_listview)
//        val lists = mutableListOf<String>()
//        lists.add("珠海水资源")
//        lists.add("中铁987")
//        lists.add("洛宁项目")
//        lists.add("宁海项目")
//        val projectAdapter = ProjectAdapter(activity!!, lists)
//        project_listview.adapter = projectAdapter
        project_listview.onItemClickListener = this
        mViewModel.requestOfProject()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(activity!!, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(activity!!, {
                ToastManager.show(it)
            })
            projects.observe(activity!!, {
                lists = it
                val projectAdapter = ProjectAdapter(activity!!, it)
                project_listview.adapter = projectAdapter
            })
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lists.takeIf {
            it.size > 0
        }?.apply {
            val json = Gson().toJson(lists[position].children)
            SectionActivity.startActivity(activity!!, json, lists[position].projectName)
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(activity!!, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}