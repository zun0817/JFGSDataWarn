package com.ztzb.data.model.data

data class WarnDeviceBean(
    val deviceCode: String,
    val deviceId: Int,
    val deviceName: String,
    val deviceType: String,
    val id: Int,
    val intervalName: String,
    val warningName: String,
    val warningTime: Long,
    val warningType: String
)