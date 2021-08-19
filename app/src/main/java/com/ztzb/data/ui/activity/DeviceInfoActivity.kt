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
        fun startActivity(activity: Activity, projectName: String, typeName: String, id: Int) {
            val intent = Intent()
            intent.setClass(activity, DeviceInfoActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("typeName", typeName)
            intent.putExtra("projectName", projectName)
            intent.putExtra("sectionName", typeName)
            intent.putExtra("projectName", projectName)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_info)
        initView()
    }

    private fun initView(){
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
        when(v!!.id){
            R.id.device_back_fl -> {
                this.finish()
            }
            R.id.device_data_tv -> {

            }
            R.id.device_realtiming_tv -> {

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