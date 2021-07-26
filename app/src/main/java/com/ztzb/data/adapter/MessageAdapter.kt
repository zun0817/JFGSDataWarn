package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R

class MessageAdapter(private var context: Context, private var list: MutableList<String>) :
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
            viewHoler.message_project_tv = view.findViewById(R.id.message_project_tv)
            viewHoler.message_section_tv = view.findViewById(R.id.message_section_tv)
            viewHoler.message_date_tv = view.findViewById(R.id.message_date_tv)
            viewHoler.message_content_tv = view.findViewById(R.id.message_content_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.message_project_tv!!.text = list[position] + "珠江水资源配置" + position
        viewHoler.message_section_tv!!.text = list[position] + "粤海26-标段区间" + position
        viewHoler.message_date_tv!!.text = list[position]
        viewHoler.message_content_tv!!.text = list[position] + "泥饼风险" + position
        return view!!
    }

    class ViewHoler {
        var message_project_tv: TextView? = null
        var message_section_tv: TextView? = null
        var message_date_tv: TextView? = null
        var message_content_tv: TextView? = null
    }
}