package com.ztzb.data.http.http

/**
 * 作者： Zun.
 * 创建： 2018/11/6.
 * 说明： App统一接口管理类
 */
object HttpUrl {

    // 登录接口
    const val LOGIN = "index/User/UserLogin"

    // 发送验证码
    const val SEND_SMS = "index/User/sendSms"

    // 重置密码
    const val RESET_PASSWORD = "index/User/passwordedit"

    // 用户详情
    const val USER_DETAIL = "/index/User/userDetail"

    // 退出登录
    const val LOGIN_OUT = "index/User/loginOut"

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

    // 泥饼风险设定
    const val MUDCAKERISK_EDIT = "yuehai/mudcakeRisk/addOrEdit"

    // 消息列表
    const val MESSAGES = "yuehai/warning/warningRiskList"





    // 逸风登录
    const val YIFENG_LOGIN = "https://zsjsms.guangdongwater.com:8090/oauth/authLogin"

    const val YIFENG_PROJECT = "https://zsjsms.guangdongwater.com:8090/projects/ZSJ/project/getProjectSiteTree"

    const val PHP_PROJECT = "http://114.116.21.136/index/Mobile/projectList"

    const val PHP_PROJECT_DETAIL = "http://114.116.21.136/index/Mobile/eachDidList"

    const val PHP_SAFE_WARN = "http://114.116.21.136/index/Mobile/riskWarning"

    const val PHP_RISK_WARN = "http://114.116.21.136/index/Mobile/riskManage"

    const val PHP_GEOLOG_WARN = "http://114.116.21.136/index/Mobile/badGeological"

    const val PHP_MONITOR_POINT = "http://114.116.21.136/index/Mobile/measuringPoint"

    const val PHP_MONITOR_POINT_DETAIL = "http://114.116.21.136/index/Mobile/measuringPointList"

}