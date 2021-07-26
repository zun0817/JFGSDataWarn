package com.ztzb.data.http.http

/**
 * 作者： Zun.
 * 创建： 2018/11/6.
 * 说明： App统一接口管理类
 */
object HttpUrl {

    // 登录接口
    const val LOGIN = "login/loginNoValidCode"

    // 服务器时间
    const val PROJECT = "screen/getRootTree"

    // 补光灯开关状态
    const val REGISTER_DEVICE_URL = "device/led_switch_lstatus"

    // 远程开门状态
    const val REGISTER = "device/remote_open_status"

    // 上传日志
    const val UPLOAD_LOG = "device/upload_log"

    // 二维码查询
    const val CHECK_QR_CODE = "device/check_qr_code"

    // 人脸识别
    const val FACE_RECOGNIZE = "device/face_recognize"

    // 人证对比
    const val ID_VERIFICATION = "device/face_compare"

}