package com.ztzb.data.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast


/**
 * 作者： Zun.
 * 创建： 2019/5/23.
 * 说明：
 */
class ToastManager {

    companion object {
        private var mToast: Toast? = null

        @SuppressLint("ShowToast")
        fun init(context: Context) {
            mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT)
            mToast?.apply {
                setGravity(Gravity.CENTER, 0, 190)
            }
        }

        fun show(resId: Int) {
            mToast?.apply {
                setText(resId)
                show()
            }
        }

        fun show(charSequence: CharSequence) {
            mToast?.apply {
                setText(charSequence)
                show()
            }
        }
    }
}