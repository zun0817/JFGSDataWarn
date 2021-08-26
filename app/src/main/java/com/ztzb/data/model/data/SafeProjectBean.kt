package com.ztzb.data.model.data


data class SafeProjectBeanItem(
    val children: List<ChildrenY>,
    val expanded: Boolean,
    val iconCls: String,
    val id: String,
    val leaf: Boolean,
    val level: Int,
    val nodeID: Int,
    val text: String,
    val type: String
)

data class ChildrenY(
    val ParentIDStr: String,
    val children: List<ChildrenYY>,
    val expanded: Boolean,
    val iconCls: String,
    val id: String,
    val leaf: Boolean,
    val level: Int,
    val nodeID: Int,
    val text: String,
    val type: String
)

data class ChildrenYY(
    val ParentIDStr: String,
    val children: List<ChildrenYYY>,
    val expanded: Boolean,
    val iconCls: String,
    val id: String,
    val leaf: Boolean,
    val level: Int,
    val nodeID: Int,
    val text: String,
    val type: String
)

data class ChildrenYYY(
    val ParentIDStr: String,
    val expanded: Boolean,
    val iconCls: String,
    val id: String,
    val leaf: Boolean,
    val level: Int,
    val nodeID: Int,
    val text: String,
    val type: String
)