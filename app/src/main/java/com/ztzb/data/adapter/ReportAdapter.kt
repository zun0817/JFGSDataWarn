package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.ReportBean

class ReportAdapter(private var context: Context, private var list: MutableList<ReportBean>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            viewHoler = ViewHoler()
            view = LayoutInflater.from(context).inflate(R.layout.layout_report_item, null)
            viewHoler.report_name_tv = view.findViewById(R.id.report_name_tv)
            viewHoler.report_time_tv = view.findViewById(R.id.report_time_tv)
            viewHoler.report_opt_tv = view.findViewById(R.id.report_opt_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.report_name_tv!!.text = list[position].name
        viewHoler.report_time_tv!!.text = list[position].time
        viewHoler.report_opt_tv!!.setOnClickListener {

        }
        return view!!
    }

    class ViewHoler {
        var report_name_tv: TextView? = null
        var report_time_tv: TextView? = null
        var report_opt_tv: TextView? = null
    }
}