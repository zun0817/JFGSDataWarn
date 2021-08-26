package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.base.BaseActivity
import com.ztzb.data.model.data.RiskWarnBean
import com.ztzb.data.util.TimeUtil
import kotlinx.android.synthetic.main.activity_risk_warn_detail.*

class RiskWarnDetailActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private val TAG = RiskWarnDetailActivity::class.java.simpleName
        fun startActivity(activity: Activity, data: String) {
            val intent = Intent()
            intent.setClass(activity, RiskWarnDetailActivity::class.java)
            intent.putExtra("data", data)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_warn_detail)
        initView()
    }

    private fun initView() {
        risk_warn_detail_back_fl.setOnClickListener(this)
        val data = intent.getStringExtra("data")
        val riskWarnBean = JSON.parseObject(data, RiskWarnBean::class.java)
        risk_detail_devicename_tv.text = "设备名称: " + riskWarnBean.dun_name
        risk_detail_deviceproduct_tv.text = "设备型号: " + riskWarnBean.dun_size
        risk_detail_devicenumber_tv.text = "设备编号: " + riskWarnBean.dun_code
        risk_detail_warntime_tv.text =
            "预警时间:: " + TimeUtil.getDateTimeFromMillisecond(riskWarnBean.createtime)
        risk_detail_yuehai_tv.text = "粤海编号: 粤海" + riskWarnBean.dun_yue_code + "号"
        risk_detail_area_tv.text = "掘进区间: " + riskWarnBean.dun_jue_area
        risk_detail_risktype_tv.text = "风险类型: " + riskWarnBean.type
        risk_detail_closewarn_tv.text = "接近报警距离: " + riskWarnBean.close_warning + "m"
        risk_detail_disappear_tv.text = "报警消失距离: " + riskWarnBean.disappear_warning + "m"
        risk_detail_riskevent_tv.text = riskWarnBean.event
        risk_detail_describe_tv.text = riskWarnBean.describe
        risk_detail_construction_tv.text = riskWarnBean.construction_scheme

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.risk_warn_detail_back_fl -> this.finish()
        }
    }
}