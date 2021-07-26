package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.MonitorBean

class MonitorAdapter(private var context: Context, private var list: MutableList<MonitorBean>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            viewHoler = ViewHoler()
            view = LayoutInflater.from(context).inflate(R.layout.layout_monitor_item, null)
            viewHoler.monitor_label_tv = view.findViewById(R.id.monitor_label_tv)
            viewHoler.monitor_value_tv = view.findViewById(R.id.monitor_value_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.monitor_label_tv!!.text = list[position].key
        viewHoler.monitor_value_tv!!.text = list[position].value
        return view!!
    }

    class ViewHoler {
        var monitor_label_tv: TextView? = null
        var monitor_value_tv: TextView? = null
    }
}