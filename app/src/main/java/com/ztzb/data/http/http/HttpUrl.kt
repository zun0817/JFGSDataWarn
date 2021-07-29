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

    // 设备监控
    const val MONITOR = "deviceMonitor/monitorInfoAddRisk"

    // 风险预警设备数量统计
    const val WARN_COUNT = "yuehai/warning/count"

    // 某种风险类型的所有的设备及信息
    const val WARN_DEVICE = "yuehai/warning/devices"

    // 风险详情
    const val WARN_DEVICE_DETAIL = "yuehai/warning/devices"

    // 沉降风险设定
    const val SUBSIDERISK_EDIT = "yuehai/subsideRisk/addOrEdit"

    // 卡盾风险设定
    const val STUCKSHIELDRISK_EDIT = "yuehai/stuckshieldRisk/addOrEdit"

    // 泥饼风险设定
    const val MUDCAKERISK_EDIT = "yuehai/mudcakeRisk/addOrEdit"

    // 消息列表
    const val MESSAGES = "yuehai/warning/warningRiskList"

}