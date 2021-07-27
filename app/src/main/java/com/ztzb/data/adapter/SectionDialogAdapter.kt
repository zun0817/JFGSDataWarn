package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.ChildrenX

class SectionDialogAdapter(private var context: Context, private var list: MutableList<ChildrenX>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_section_item, null)
            viewHoler = ViewHoler()
            viewHoler.section_tv = view.findViewById(R.id.section_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.section_tv!!.text = list[position].projectName + "~" + list[position].groupName
        return view!!
    }

    class ViewHoler {
        var section_tv: TextView? = null
    }
}