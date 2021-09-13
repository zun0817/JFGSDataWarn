package com.ztzb.data.model.data


data class SectionBean(
    val data: List<SectionItem>,
    val id: Int,
    val name: String
)

data class SectionItem(
    val actual_time: String,
    val code: Any,
    val dun_name: String,
    val excavating_tunnel: String,
    val expect_time: String,
    val id: Int,
    val licheng: Int,
    val name: String,
    val pid: Int
)