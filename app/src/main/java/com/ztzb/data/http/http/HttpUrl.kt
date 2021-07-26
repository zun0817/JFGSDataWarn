package com.ztzb.data.http.http

/**
 * 作者： Zun.
 * 创建： 2018/11/6.
 * 说明： App统一接口管理类
 */
object HttpUrl {

    /**
     * 新接口
     */
    // 注册设备
    const val REGISTER = "device/register"

    // 服务器时间
    const val SERVER_TIME = "device/server_time"

    // 补光灯开关状态
    const val LED_SWITCH_STATUS = "device/led_switch_status"

    // 远程开门状态
    const val REMOTE_OPEN_STATUS = "device/remote_open_status"

    // 上传日志
    const val UPLOAD_LOG = "device/upload_log"

    // 二维码查询
    const val CHECK_QR_CODE = "device/check_qr_code"

    // 人脸识别
    const val FACE_RECOGNIZE = "device/face_recognize"

    // 人证对比
    const val ID_VERIFICATION = "device/face_compare"

    /**
     * 老接口
     */
    // 注册设备
    const val REGISTER_DEVICE_URL = "device/regist.do"

    // 人脸查询
    const val FACE_RECOGNITION_URL = "identify/face.do"

    // 二维码查询
    const val QRCODE_RECOGNITION_URL = "identify/qrcode.do"

    // 访客二维码查询
    const val VISITOR_QRCODE_RECOGNITION_URL = "visitor/qr_search.do"

    // 日志上传
    const val UPLOAD_LOG_URL = "device/upload_log.do"

    // 身份证验证
    const val ID_VERIFICATION_URL = "face/compare.do"

    // 远程开门状态
    const val REMOTE_OPEN_STATE_URL = "entrance/state.do"

    // 获取时间轮循接口
    const val DEVICE_TIMER = "device/timer.do"

    // 异常报警
    const val ABNORMAL_MONITOR = "device/heartbeat.do"

}