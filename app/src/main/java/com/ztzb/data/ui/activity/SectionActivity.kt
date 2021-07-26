package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.ztzb.data.R
import com.ztzb.data.adapter.SectionAdapter
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.view.SectionDialog
import com.ztzb.data.viewmodel.SectionViewModel
import kotlinx.android.synthetic.main.activity_section.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SectionActivity : BaseMVVMActivity(),
    AdapterView.OnItemClickListener, View.OnClickListener {

    companion object {
        private val TAG = SectionActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SectionActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var dialogList: MutableList<String>

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
        val lists = mutableListOf<String>()
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")
        lists.add("水资源配置工程A7标")

        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")
        dialogList.add("粤海26 GZ13-1#~Z13#左线")

        val sectionAdapter = SectionAdapter(this, lists)
        section_listview.adapter = sectionAdapter
        section_listview.onItemClickListener = this
        back_fl.setOnClickListener(this)
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val sectionDialog = SectionDialog(this, dialogList)
        sectionDialog.show()
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