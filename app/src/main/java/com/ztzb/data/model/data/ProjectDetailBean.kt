package com.ztzb.data.model.data

data class ProjectDetailBean(
    val areaId: Int,
    val areaName: String,
    val areaPath: String,
    val brief: String,
    val enable: Int,
    val endMileage: Double,
    val endTime: String,
    val finish: Int,
    val id: Int,
    val parentAreaId: Int,
    val parentAreaName: String,
    val phone: String,
    val projectCode: String,
    val projectManager: String,
    val projectName: String,
    val projectNameAll: String,
    val remark: String,
    val shiftInfo: String,
    val sortNum: Int,
    val startMileage: Double,
    val startTime: String,
    val tunnelLength: Double
)