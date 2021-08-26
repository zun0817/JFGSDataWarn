package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.base.BaseActivity
import com.ztzb.data.model.data.GeologicalBean
import com.ztzb.data.util.TimeUtil
import kotlinx.android.synthetic.main.activity_geological_detail.*

class GeologicalDetailActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private val TAG = GeologicalDetailActivity::class.java.simpleName
        fun startActivity(activity: Activity, data: String) {
            val intent = Intent()
            intent.setClass(activity, GeologicalDetailActivity::class.java)
            intent.putExtra("data", data)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geological_detail)
        initView()
    }

    private fun initView() {
        geological_warn_detail_back_fl.setOnClickListener(this)
        val data = intent.getStringExtra("data")
            val geologicalBean = JSON.parseObject(data, GeologicalBean::class.java)
        geological_detail_devicename_tv.text = "设备名称: " + geologicalBean.dun_name
        geological_detail_deviceproduct_tv.text = "设备型号: " + geologicalBean.dun_size
        geological_detail_devicenumber_tv.text = "设备编号: " + geologicalBean.dun_code
        geological_detail_warntime_tv.text =
            "预警时间:: " + TimeUtil.getDateTimeFromMillisecond(geologicalBean.createtime)
        geological_detail_yuehai_tv.text = "粤海编号: 粤海" + geologicalBean.dun_yue_code + "号"
        geological_detail_area_tv.text = "掘进区间: " + geologicalBean.dun_jue_area
        geological_detail_geotype_tv.text = "不良地质类型: " + geologicalBean.type
        geological_detail_level_tv.text = "不良地质等级: " + geologicalBean.level
        geological_detail_start_tv.text = "开始里程: " + geologicalBean.start + "m"
        geological_detail_end_tv.text = "结束里程: " + geologicalBean.end + "m"
        geological_detail_describe_tv.text = geologicalBean.describe
        geological_detail_status_tv.text = "状态: " + geologicalBean.status
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.geological_warn_detail_back_fl -> this.finish()
        }
    }
}