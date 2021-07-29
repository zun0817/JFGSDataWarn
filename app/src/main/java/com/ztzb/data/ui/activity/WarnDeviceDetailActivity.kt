package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.WarnDeviceDetailViewModel
import kotlinx.android.synthetic.main.activity_warn_device_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WarnDeviceDetailActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = WarnDeviceDetailActivity::class.java.simpleName
        fun startActivity(
            activity: Activity,
            warningType: String,
            id: Int,
            intervalName: String
        ) {
            val intent = Intent()
            intent.setClass(activity, WarnDeviceDetailActivity::class.java)
            intent.putExtra("warningType", warningType)
            intent.putExtra("intervalName", intervalName)
            intent.putExtra("id", id)
            activity.startActivity(intent)
        }
    }

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: WarnDeviceDetailViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warn_device_detail)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        warn_detail_fl.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val id = intent.getIntExtra("id", 0)
        val warningType = intent.getStringExtra("warningType")
        warningType?.let { mViewModel.requestOfWarnDeviceDetail(it, id) }
    }

    override fun onClick(v: View?) {
        this.finish()
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@WarnDeviceDetailActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@WarnDeviceDetailActivity, {
                ToastManager.show(it)
            })
            warnDeviceDetailBean.observe(this@WarnDeviceDetailActivity, {
                detail_devicecode_tv.text = "设备编号： " + it.deviceCode
                detail_section_tv.text = "标段名称： " + it.sectionName
                detail_interval_tv.text = "区间名称： " + it.intervalName
                detail_warntime_tv.text = "预警时间： " + it.warningTime
                if (it.mudcakeRiskType != null) {
                    when (it.mudcakeRiskType) {
                        1 -> {
                            detail_key1_tv.text = "开始环号"
                            detail_key2_tv.text = "地质情况"
                            detail_key3_tv.text = "渣土温度测量值"
                            detail_key4_tv.text = "渣土温度设定值"
                            detail_value1_tv.text = it.ringNumStart.toString() + "环"
                            detail_value2_tv.text = it.geologSoil
                            detail_value3_tv.text = it.soilTemperatureReal.toString() + "℃"
                            detail_value4_tv.text = it.soilTemperatureSet.toString() + "℃"
                        }
                        2 -> {
                            detail_key1_tv.text = "总推力实际值"
                            detail_key2_tv.text = "理论总推力设定值"
                            detail_key3_tv.text = "扭矩实际值"
                            detail_key4_tv.text = "理论扭矩设定值"
                            detail_value1_tv.text = it.thrustReal.toString() + "kN"
                            detail_value2_tv.text = it.thrustSet.toString() + "kN"
                            detail_value3_tv.text = it.torqueReal.toString() + "kN·m"
                            detail_value4_tv.text = it.torqueSet.toString() + "kN·m"
                        }
                    }
                }
                if (it.subsideRiskType != null) {
                    when (it.subsideRiskType) {
                        1 -> {
                            detail_key1_tv.text = "开始环号"
                            detail_key2_tv.text = "地质情况"
                            detail_key3_tv.text = "本环掘进出土量"
                            detail_key4_tv.text = "理论出土体积设定值"
                            detail_value1_tv.text = it.ringNumStart.toString() + "环"
                            detail_value2_tv.text = it.geologSoil
                            detail_value3_tv.text = it.currentRingSoil.toString() + "m³"
                            detail_value4_tv.text = it.theorySoilVolume.toString() + "m³"
                        }
                        2 -> {
                            detail_key1_tv.text = "开始环号"
                            detail_key2_tv.text = "地质情况"
                            detail_key3_tv.text = "本环注浆量"
                            detail_key4_tv.text = "理论注浆设定值"
                            detail_value1_tv.text = it.ringNumStart.toString() + "环"
                            detail_value2_tv.text = it.geologSoil
                            detail_value3_tv.text = it.currentRingGrout.toString() + "L"
                            detail_value4_tv.text = it.theoryGroutVolume.toString() + "L"
                        }
                    }
                }
                if (it.stuckRiskType != null) {
                    when (it.stuckRiskType) {
                        1 -> {
                            detail_key1_tv.text = "开始环号"
                            detail_key2_tv.text = "地质情况"
                            detail_key3_tv.text = "边刀磨损实际值"
                            detail_key4_tv.text = "边刀磨损设定值"
                            detail_value1_tv.text = it.ringNumStart.toString() + "环"
                            detail_value2_tv.text = it.geologSoil
                            detail_value3_tv.text = it.edgeknifeWearReal.toString() + "mm"
                            detail_value4_tv.text = it.edgeknifeWearSet.toString() + "mm"
                        }
                        2 -> {
                            detail_key1_tv.text = "开始环号"
                            detail_key2_tv.text = "地质情况"
                            detail_key3_tv.text = "总推力实际值"
                            detail_key4_tv.text = "理论总推力设定值"
                            detail_value1_tv.text = it.ringNumStart.toString() + "环"
                            detail_value2_tv.text = it.geologSoil
                            detail_value3_tv.text = it.thrustReal.toString() + "kN"
                            detail_value4_tv.text = it.thrustSet.toString() + "kN"
                        }
                    }
                }
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@WarnDeviceDetailActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}
