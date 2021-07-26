package com.ztzb.data.util

import android.text.TextUtils


/**
 * 作者： Zun.
 * 创建： 2019/4/24.
 * 说明：
 */
object ConvertUtil {

    //把String转化为float
    fun convertToFloat(number: String, defaultValue: Float): Float {
        if (TextUtils.isEmpty(number)) {
            return defaultValue
        }
        return try {
            java.lang.Float.parseFloat(number)
        } catch (e: Exception) {
            defaultValue
        }
    }

    //把String转化为double
    fun convertToDouble(number: String, defaultValue: Double): Double {
        if (TextUtils.isEmpty(number)) {
            return defaultValue
        }
        return try {
            java.lang.Double.parseDouble(number)
        } catch (e: Exception) {
            defaultValue
        }
    }

    //把String转化为int
    fun convertToInt(number: String, defaultValue: Int): Int {
        if (TextUtils.isEmpty(number)) {
            return defaultValue
        }
        return try {
            Integer.parseInt(number)
        } catch (e: Exception) {
            defaultValue
        }
    }

}