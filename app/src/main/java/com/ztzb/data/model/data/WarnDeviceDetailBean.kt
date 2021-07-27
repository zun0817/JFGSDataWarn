package com.ztzb.data.model.data

data class WarnDeviceDetailBean(
    val createTime: Long,
    val deviceCode: String,
    val deviceId: Int,
    val deviceName: String,
    val deviceType: String,
    val geologSoil: String,
    val id: Int,
    val mudcakeRiskType: Int,
    val remark: String,
    val ringNumEnd: Int,
    val ringNumStart: Int,
    val soilTemperatureReal: Double,
    val soilTemperatureSet: Double,
    val status: String,
    val updateTime: Long,
    val warningTime: Long
)