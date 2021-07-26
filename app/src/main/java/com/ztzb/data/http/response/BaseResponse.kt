package com.ztzb.data.http.response

/**
 * 作者： Zun.
 * 创建： 2018/11/6.
 * 说明： 返回数据基础类
 */
class BaseResponse<T>(var data: T, var msg: String, var code: Int) {

    companion object {
        val SUCCESS = 0
        val FAIL = 1
    }

}
