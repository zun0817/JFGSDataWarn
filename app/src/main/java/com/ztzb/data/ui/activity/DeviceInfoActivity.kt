package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ztzb.data.R
import com.ztzb.data.base.BaseActivity
import kotlinx.android.synthetic.main.activity_device_info.*

class DeviceInfoActivity : BaseActivity(), View.OnClickListener {


    companion object {
        private val TAG = DeviceInfoActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            projectName: String,
            typeName: String,
            deviceName: String,
            sectionName: String,
            areaName: String,
            id: Int
        ) {
            val intent = Intent()
            intent.setClass(activity, DeviceInfoActivity::class.java)
            intent.putExtra("deviceName", deviceName)
            intent.putExtra("typeName", typeName)
            intent.putExtra("projectName", projectName)
            intent.putExtra("sectionName", sectionName)
            intent.putExtra("areaName", areaName)
            intent.putExtra("id", id)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)
        initView()
    }

    private fun initView() {
        device_project_tv.text = "项目名称：" + intent.getStringExtra("projectName")
        device_section_tv.text = "标段名称：" + intent.getStringExtra("sectionName")
        device_area_tv.text = "区间名称：" + intent.getStringExtra("areaName")
        device_no_tv.text = "设备编号：" + intent.getStringExtra("deviceName")
        device_type_tv.text = "设备类型：" + intent.getStringExtra("typeName")

        device_back_fl.setOnClickListener(this)
        device_data_tv.setOnClickListener(this)
        device_realtiming_tv.setOnClickListener(this)
        device_main_tv.setOnClickListener(this)
        device_guide_tv.setOnClickListener(this)
        device_help_tv.setOnClickListener(this)
        device_statistic_tv.setOnClickListener(this)
        device_progress_tv.setOnClickListener(this)
        device_work_tv.setOnClickListener(this)
        device_alarm_tv.setOnClickListener(this)
        device_consumables_tv.setOnClickListener(this)
        device_pulp_tv.setOnClickListener(this)
        device_sealup_tv.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.device_back_fl -> {
                this.finish()
            }
            R.id.device_data_tv -> {
                val projectName = intent.getStringExtra("projectName")
                val sectionName = intent.getStringExtra("sectionName")
                val areaName = intent.getStringExtra("areaName")
                val deviceName = intent.getStringExtra("deviceName")
                val typeName = intent.getStringExtra("typeName")
                ReportActivity.startActivity(
                    this,
                    projectName!!,
                    sectionName!!,
                    areaName!!,
                    deviceName!!,
                    typeName!!
                )
            }
            R.id.device_realtiming_tv -> {
                val areaName = intent.getStringExtra("areaName")
                val id = intent.getIntExtra("id", 0)
                val typeName = intent.getStringExtra("typeName")
                MonitorActivity.startActivity(this, areaName!!, typeName!!, id)
            }
            R.id.device_main_tv -> {

            }
            R.id.device_guide_tv -> {

            }
            R.id.device_help_tv -> {

            }
            R.id.device_statistic_tv -> {

            }
            R.id.device_progress_tv -> {

            }
            R.id.device_work_tv -> {

            }
            R.id.device_alarm_tv -> {

            }
            R.id.device_consumables_tv -> {

            }
            R.id.device_pulp_tv -> {

            }
            R.id.device_sealup_tv -> {

            }
        }
    }
}