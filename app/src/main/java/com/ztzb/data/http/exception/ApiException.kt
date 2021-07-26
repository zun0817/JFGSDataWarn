package com.ztzb.data.http.exception


/**
 * 作者： Zun.
 * 创建： 2018/11/9.
 * 说明： 请求异常封装
 */
class ApiException(var code: Int, var msg: String?) : RuntimeException(msg)