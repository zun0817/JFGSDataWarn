  package com.ztzb.data.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.SafeMoitorPointBean
import com.ztzb.data.ui.activity.MonitorPointDetailActivity

class MonitorPointAdapter(private var context: Context, private var list: MutableList<SafeMoitorPointBean.DataDTO>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            viewHoler = ViewHoler()
            view = LayoutInflater.from(context).inflate(R.layout.layout_monitor_point_item, null)
            viewHoler.point_no_tv = view.findViewById(R.id.point_no_tv)
            viewHoler.point_type_tv = view.findViewById(R.id.point_type_tv)
            viewHoler.point_count_tv = view.findViewById(R.id.point_count_tv)
            viewHoler.point_time_tv = view.findViewById(R.id.point_time_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.point_no_tv!!.text = list[position].sensorCode
        viewHoler.point_no_tv!!.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        viewHoler.point_type_tv!!.text = list[position].sensorTypeName
        viewHoler.point_count_tv!!.text = list[position].meaFrequency
        viewHoler.point_time_tv!!.text = list[position].maxTime
        viewHoler.point_no_tv!!.setOnClickListener {
            MonitorPointDetailActivity.startActivity(context as Activity, list[position].id)
        }
        return view!!
    }

    class ViewHoler {
        var point_no_tv: TextView? = null
        var point_type_tv: TextView? = null
        var point_count_tv: TextView? = null
        var point_time_tv: TextView? = null
    }
}