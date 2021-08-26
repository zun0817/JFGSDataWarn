package com.ztzb.data.http.common

/**
 * 作者： Zun.
 * 创建： 2018/11/7.
 * 说明： 全局变量
 */
object Constants {

    const val PATH_CACHE = "/storage/emulated/0/Android/data/com.ztzb.data/cache/GFGSCache"

    const val BASE_URL = "https://plat.tbmcloud.com.cn/device-ai/"

    const val YIFENG_URL = "zsjsms.guangdongwater.com:8090/"

    const val ORIGIN_URL = "plat.tbmcloud.com.cn/device-ai/"

    /**
     * 未知错误
     */
    const val ERROR_UNKNOWN: Int = 1000

    /**
     * 解析错误
     */
    const val ERROR_PARSE: Int = 1001

    /**
     * 网络错误
     */
    const val ERROR_NETWORK: Int = 1002

    /**
     * HTTP错误
     */
    const val ERROR_HTTP: Int = 1003

    /**
     * 连接超时
     */
    const val ERROR_TIMEOUT = 1004

    /**
     * 证书出错
     */
    const val ERROR_SSL = 1005

    /**
     * Token失效
     */
    const val ERROR_TOKEN_INVALIB = "10008"

    /**
     * 登录失效
     */
    const val ERROR_LOGIN_INVALIB = "10009"

    /**
     * 网络连接超时时间
     */
    const val REQUEST_CONNECT_TIMEOUT = 20L

    /**
     * 读取数据超时时间
     */
    const val REQUEST_READ_TIMEOUT = 20L

    /**
     * 写入数据超时时间
     */
    const val REQUEST_WRITE_TIMEOUT = 20L


}