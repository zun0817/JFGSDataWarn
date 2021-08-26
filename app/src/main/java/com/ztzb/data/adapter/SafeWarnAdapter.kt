package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.SafeWarnBean

class SafeWarnAdapter(private var context: Context, private var list: MutableList<SafeWarnBean>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_safe_warn_item, null)
            viewHoler = ViewHoler()
            viewHoler.safe_item_interval_tv = view.findViewById(R.id.safe_item_interval_tv)
            viewHoler.safe_item_type_tv = view.findViewById(R.id.safe_item_type_tv)
            viewHoler.safe_item_senior_tv = view.findViewById(R.id.safe_item_senior_tv)
            viewHoler.safe_item_warntime_tv = view.findViewById(R.id.safe_item_warntime_tv)
            viewHoler.safe_item_warnname_tv = view.findViewById(R.id.safe_item_warnname_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.safe_item_interval_tv!!.text = list[position].siteNames
        when (list[position].warnType) {
            1 -> viewHoler.safe_item_type_tv!!.text = "预警类型: 应力"
            2 -> viewHoler.safe_item_type_tv!!.text = "预警类型: 沉降"
            3 -> viewHoler.safe_item_type_tv!!.text = "预警类型: 倾斜"
            4 -> viewHoler.safe_item_type_tv!!.text = "预警类型: 地下水"
        }
        viewHoler.safe_item_senior_tv!!.text = "预警等级: " + list[position].gradeName
        viewHoler.safe_item_warntime_tv!!.text =
            "预警时间: " + list[position].updateTime
        viewHoler.safe_item_warnname_tv!!.text = list[position].eventName

        return view!!
    }

    class ViewHoler {
        var safe_item_interval_tv: TextView? = null
        var safe_item_type_tv: TextView? = null
        var safe_item_senior_tv: TextView? = null
        var safe_item_warntime_tv: TextView? = null
        var safe_item_warnname_tv: TextView? = null
    }
}