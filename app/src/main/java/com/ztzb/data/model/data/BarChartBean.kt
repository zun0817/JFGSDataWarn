package com.ztzb.data.model.data

data class BarChartBean(
    val vtDateValue: MutableList<VtDateValue>,
    val vtDateValueAvg: MutableList<VtDateValueAvg>
)

data class VtDateValue(
    val fValue: Int,
    val sYearMonth: String
)

data class VtDateValueAvg(
    val fValue: Float,
    val sYearMonth: String
)