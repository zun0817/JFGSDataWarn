package com.ztzb.data.view

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.widget.AppCompatButton
import com.ztzb.data.R
import com.ztzb.data.adapter.SectionDialogAdapter
import com.ztzb.data.ui.activity.MonitorActivity
import com.ztzb.data.ui.activity.SectionActivity


class SectionDialog(context: Context, list: MutableList<String>) : Dialog(context),
    View.OnClickListener, AdapterView.OnItemClickListener {

    private var mContext: Context = context
    private var mList: MutableList<String> = list
    private lateinit var section_dialog_listview: ListView
    private lateinit var section_dialog_btn: AppCompatButton

    init {
        initView()
        initListView()
    }

    private fun initView() {
        val contentView: View = View.inflate(mContext, R.layout.layout_section_dialog, null)
        section_dialog_btn = contentView.findViewById(R.id.section_dialog_btn)
        section_dialog_listview = contentView.findViewById(R.id.section_dialog_listview)
        setContentView(contentView)
    }

    private fun initListView() {
        section_dialog_btn.setOnClickListener(this)
        section_dialog_listview.onItemClickListener = this
        val sectionDialogAdapter = SectionDialogAdapter(mContext, mList)
        section_dialog_listview.adapter = sectionDialogAdapter
    }

    override fun onClick(v: View?) {
        this.dismiss()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        MonitorActivity.startActivity(mContext as SectionActivity)
        this.dismiss()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus) {
            return
        }
        setHeight()
    }

    private fun setHeight() {
        val displayMetrics = mContext.resources.displayMetrics
        window?.let {
            val attributes: WindowManager.LayoutParams = it.attributes
            if (it.decorView.height >= (displayMetrics.heightPixels * 0.5).toInt()) {
                attributes.height = (displayMetrics.heightPixels * 0.5).toInt()
            }
            it.attributes = attributes
        }
    }

}