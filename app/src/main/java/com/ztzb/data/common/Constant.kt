package com.ztzb.data.common

import android.os.Environment
import java.io.File

/**
 * 作者： Zun.
 * 创建： 2019/4/16.
 * 说明：
 */
object Constant {

    /**
     * 日志存储路径
     */
    val LOG_PATH = File(
        Environment.getExternalStorageDirectory(),
        "JFGSData"
    ).path

    /**
     * 文件管理器的包名
     */
    val FILE_PACKAGE_NAME = "net.micode.fileexplorer"

    /**
     * 系统设置的包名
     */
    val SETTING_PACKAGE_NAME = "com.android.settings"

    /**
     * 腾讯Buly APPID
     */
    val BULY_APP_ID = "b213b89ce5"


}