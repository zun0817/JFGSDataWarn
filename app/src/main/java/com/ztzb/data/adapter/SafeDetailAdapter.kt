package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.SafeProjectDetailBean

class SafeDetailAdapter(
    private var context: Context,
    private var list: MutableList<SafeProjectDetailBean>
) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            viewHoler = ViewHoler()
            view = LayoutInflater.from(context).inflate(R.layout.layout_safe_detail_item, null)
            viewHoler.safe_detail_section_tv = view.findViewById(R.id.safe_detail_section_tv)
            viewHoler.safe_check_point_tv = view.findViewById(R.id.safe_check_point_tv)
            viewHoler.safe_check_point_data_tv = view.findViewById(R.id.safe_check_point_data_tv)
            viewHoler.safe_check_point_data_week_tv =
                view.findViewById(R.id.safe_check_point_data_week_tv)
            viewHoler.safe_check_point_data_three_tv =
                view.findViewById(R.id.safe_check_point_data_three_tv)
            viewHoler.safe_check_point_data_one_tv =
                view.findViewById(R.id.safe_check_point_data_one_tv)
            viewHoler.safe_date_tv = view.findViewById(R.id.safe_date_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.safe_detail_section_tv!!.text = list[position].siteName
        viewHoler.safe_check_point_tv!!.text = list[position].num1.toString()
        viewHoler.safe_check_point_data_tv!!.text = list[position].num2.toString()
        viewHoler.safe_check_point_data_week_tv!!.text = list[position].num4.toString()
        viewHoler.safe_check_point_data_three_tv!!.text = list[position].num5.toString()
        viewHoler.safe_check_point_data_one_tv!!.text = list[position].num6.toString()
        viewHoler.safe_date_tv!!.text = list[position].num3
        return view!!
    }

    class ViewHoler {
        var safe_detail_section_tv: TextView? = null
        var safe_check_point_tv: TextView? = null
        var safe_check_point_data_tv: TextView? = null

        var safe_check_point_data_week_tv: TextView? = null
        var safe_check_point_data_three_tv: TextView? = null
        var safe_check_point_data_one_tv: TextView? = null
        var safe_date_tv: TextView? = null
    }
}