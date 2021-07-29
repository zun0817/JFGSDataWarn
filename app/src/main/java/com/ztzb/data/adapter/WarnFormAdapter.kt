package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.WarnDeviceBean
import com.ztzb.data.util.TimeUtil

class WarnFormAdapter(private var context: Context, private var list: MutableList<WarnDeviceBean>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_warn_item, null)
            viewHoler = ViewHoler()
            viewHoler.item_interval_tv = view.findViewById(R.id.item_interval_tv)
            viewHoler.item_devicecode_tv = view.findViewById(R.id.item_devicecode_tv)
            viewHoler.item_devicetype_tv = view.findViewById(R.id.item_devicetype_tv)
            viewHoler.item_warntime_tv = view.findViewById(R.id.item_warntime_tv)
            viewHoler.item_warnname_tv = view.findViewById(R.id.item_warnname_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.item_interval_tv!!.text = "标段区间: " + list[position].intervalName
        viewHoler.item_devicecode_tv!!.text = "设备编号: " + list[position].deviceCode
        viewHoler.item_devicetype_tv!!.text = "设备类型: " + list[position].deviceType
        viewHoler.item_warntime_tv!!.text =
            "预警时间: " + TimeUtil.getDateTimeFromMillisecond(list[position].warningTime)
        viewHoler.item_warnname_tv!!.text = "预警名称: " + list[position].warningName

        return view!!
    }

    class ViewHoler {
        var item_interval_tv: TextView? = null
        var item_devicecode_tv: TextView? = null
        var item_devicetype_tv: TextView? = null
        var item_warntime_tv: TextView? = null
        var item_warnname_tv: TextView? = null
    }
}