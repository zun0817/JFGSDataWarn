package com.ztzb.data.model.data

data class MonitorBean(
    val GroupItemList: List<GroupItem>,
    val curGroup: String,
    val curRingPile: String,
    val status: String,
    val syncTime: String
)

data class GroupItem(
    val deviceName: String,
    val subItems: List<SubItem>
)

data class SubItem(
    val deviceId: Int,
    val deviceName: String,
    val id: Int,
    val itemId: Int,
    val itemName: String,
    val unit: String,
    val value: Double?
)