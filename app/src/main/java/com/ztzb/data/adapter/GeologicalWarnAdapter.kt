package com.ztzb.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ztzb.data.R
import com.ztzb.data.model.data.GeologicalBean
import com.ztzb.data.util.TimeUtil

class GeologicalWarnAdapter(private var context: Context, private var list: MutableList<GeologicalBean>) :
    BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHoler: ViewHoler? = null
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_geological_warn_item, null)
            viewHoler = ViewHoler()
            viewHoler.geological_item_devicename_tv = view.findViewById(R.id.geological_item_devicename_tv)
            viewHoler.geological_item_deviceproduct_tv =
                view.findViewById(R.id.geological_item_deviceproduct_tv)
            viewHoler.geological_item_devicenember_tv = view.findViewById(R.id.geological_item_devicenember_tv)
            viewHoler.geological_item_risktype_tv = view.findViewById(R.id.geological_item_risktype_tv)
            viewHoler.geological_item_yuehai_tv = view.findViewById(R.id.geological_item_yuehai_tv)
            viewHoler.geological_item_warntime_tv = view.findViewById(R.id.geological_item_warntime_tv)
            viewHoler.geological_item_area_tv = view.findViewById(R.id.geological_item_area_tv)
            view.tag = viewHoler
        } else {
            view = convertView
            viewHoler = view.tag as ViewHoler
        }
        viewHoler.geological_item_devicename_tv!!.text = "设备名称: " + list[position].dun_name
        viewHoler.geological_item_deviceproduct_tv!!.text = "设备型号: " + list[position].dun_size
        viewHoler.geological_item_devicenember_tv!!.text = "设备编号: " + list[position].dun_code
        viewHoler.geological_item_risktype_tv!!.text = "风险类型: " + list[position].type
        viewHoler.geological_item_yuehai_tv!!.text = "粤海编号: 粤海" + list[position].dun_yue_code + "号"
        viewHoler.geological_item_warntime_tv!!.text =
            "预警时间: " + TimeUtil.getDateTimeFromMillisecond(list[position].createtime)
        viewHoler.geological_item_area_tv!!.text = "掘金区间: " + list[position].dun_jue_area

        return view!!
    }

    class ViewHoler {
        var geological_item_devicename_tv: TextView? = null
        var geological_item_deviceproduct_tv: TextView? = null
        var geological_item_devicenember_tv: TextView? = null
        var geological_item_risktype_tv: TextView? = null
        var geological_item_yuehai_tv: TextView? = null
        var geological_item_warntime_tv: TextView? = null
        var geological_item_area_tv: TextView? = null
    }
}