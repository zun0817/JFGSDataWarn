package com.ztzb.data.model.data

data class ProjectBean(
    val children: List<Children>,
    val id: Int,
    val projectCode: String,
    val projectName: String
)

data class Children(
    val children: List<ChildrenX>,
    val id: Int,
    val projectName: String
)

data class ChildrenX(
    val groupCode: String,
    val groupName: String,
    val groupType: Int,
    val id: Int,
    val projectCode: String,
    val projectName: String,
    val typeName: String
)