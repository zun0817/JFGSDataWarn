package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.Result
import com.ztzb.data.util.TimeUtil

class MessageAdapter(private var context: Context, private var list: MutableList<Result>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_message_item, null)
            viewHoler = ViewHoler()
            viewHoler.message_devicecode_tv = view.findViewById(R.id.message_devicecode_tv)
            viewHoler.message_section_tv = view.findViewById(R.id.message_section_tv)
            viewHoler.message_interval_tv = view.findViewById(R.id.message_interval_tv)
            viewHoler.message_risktype_tv = view.findViewById(R.id.message_risktype_tv)
            viewHoler.message_warntime_tv = view.findViewById(R.id.message_warntime_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.message_devicecode_tv!!.text = "设备编号：" + list[position].deviceCode
        viewHoler.message_section_tv!!.text = "标段名称：" + list[position].sectionName
        viewHoler.message_interval_tv!!.text = "区间名称：" + list[position].intervalName
        viewHoler.message_warntime_tv!!.text =
            "预警时间：" + TimeUtil.getDateFromMillisecond(list[position].warningTime)
        when(list[position].riskType){
            1 -> {
                viewHoler.message_risktype_tv!!.text = "风险类型：沉降风险"
            }
            2 -> {
                viewHoler.message_risktype_tv!!.text = "风险类型：泥饼风险"
            }
            3 -> {
                viewHoler.message_risktype_tv!!.text = "风险类型：卡顿风险"
            }
        }
        return view!!
    }

    class ViewHoler {
        var message_devicecode_tv: TextView? = null
        var message_section_tv: TextView? = null
        var message_interval_tv: TextView? = null
        var message_risktype_tv: TextView? = null
        var message_warntime_tv: TextView? = null
    }
}