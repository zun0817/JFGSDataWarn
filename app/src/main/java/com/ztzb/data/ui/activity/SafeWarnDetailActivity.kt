package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.fastjson.JSON
import com.ztzb.data.R
import com.ztzb.data.base.BaseActivity
import com.ztzb.data.model.data.SafeWarnBean
import kotlinx.android.synthetic.main.activity_safe_warn_detail.*

class SafeWarnDetailActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private val TAG = SafeWarnDetailActivity::class.java.simpleName
        fun startActivity(activity: Activity, data: String) {
            val intent = Intent()
            intent.setClass(activity, SafeWarnDetailActivity::class.java)
            intent.putExtra("data", data)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_warn_detail)
        initView()
    }

    private fun initView() {
        safe_warn_detail_back_fl.setOnClickListener(this)
        val data = intent.getStringExtra("data")
        val safeWarnBean = JSON.parseObject(data, SafeWarnBean::class.java)
        safe_detail_interval_tv.text = safeWarnBean.siteNames
        safe_detail_warnname_tv.text = safeWarnBean.eventName
        when (safeWarnBean.warnType) {
            1 -> safe_detail_type_tv.text = "预警类型: 应力"
            2 -> safe_detail_type_tv.text = "预警类型: 沉降"
            3 -> safe_detail_type_tv.text = "预警类型: 倾斜"
            4 -> safe_detail_type_tv.text = "预警类型: 地下水"
        }
        safe_detail_senior_tv.text = "预警等级: " + safeWarnBean.gradeName
        safe_detail_warntime_tv.text = "预警时间: " + safeWarnBean.updateTime
        safe_detail_person_tv.text = "更新人: " + safeWarnBean.realName
        safe_detail_describe_tv.text = safeWarnBean.describe
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.safe_warn_detail_back_fl -> this.finish()
        }
    }
}