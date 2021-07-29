package com.ztzb.data.model.data

data class MessageBean(
    val pageNumber: Int,
    val pageSize: Int,
    val result: MutableList<Result>,
    val totalElements: Int,
    val totalPages: Int
)

data class Result(
    val createTime: Long,
    val deviceCode: String,
    val id: Int,
    val intervalName: String,
    val riskType: Int,
    val sectionName: String,
    val warningTime: Long
)