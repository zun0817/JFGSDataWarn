package com.ztzb.data.model.data

data class AutoLoginBean(
    val RealName: String,
    val RoleID: Int,
    val RoleName: String,
    val RoleType: Int,
    val UserID: Int,
    val code: Int,
    val login: String,
    val loginStatus: Int,
    val msg: String
)