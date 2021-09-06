package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.MonitorPointDetailBean
import com.ztzb.data.util.BigDecimalUtils
import java.math.BigDecimal

class MonitorPointDetailAdapter(private var context: Context, private var list: MutableList<MonitorPointDetailBean.PointDetail>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            viewHoler = ViewHoler()
            view = LayoutInflater.from(context).inflate(R.layout.layout_monitor_point_detail_item, null)
            viewHoler.point_detail_time_tv = view.findViewById(R.id.point_detail_time_tv)
            viewHoler.point_detail_value_tv = view.findViewById(R.id.point_detail_value_tv)
            viewHoler.point_detail_move_tv = view.findViewById(R.id.point_detail_move_tv)
            viewHoler.point_detail_rate_tv = view.findViewById(R.id.point_detail_rate_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.point_detail_time_tv!!.text = list[position].meaTime
        viewHoler.point_detail_value_tv!!.text = BigDecimalUtils.formatFive(BigDecimal(list[position].meaValue1)).toString()
        viewHoler.point_detail_move_tv!!.text = BigDecimalUtils.formatDouble(BigDecimal(list[position].resValue1)).toString()
        viewHoler.point_detail_rate_tv!!.text = BigDecimalUtils.formatFive(BigDecimal(list[position].resValue2)).toString()
        return view!!
    }

    class ViewHoler {
        var point_detail_time_tv: TextView? = null
        var point_detail_value_tv: TextView? = null
        var point_detail_move_tv: TextView? = null
        var point_detail_rate_tv: TextView? = null
    }
}