package com.ztzb.data.receiver

import android.content.Context
import android.util.Log
import cn.jpush.android.api.CustomMessage
import cn.jpush.android.api.NotificationMessage
import cn.jpush.android.service.JPushMessageReceiver

class PushMessageReceiver : JPushMessageReceiver() {

    override fun onMessage(p0: Context?, p1: CustomMessage?) {
        super.onMessage(p0, p1)
        Log.e("$$$$$$", "接收了")
    }

    override fun onInAppMessageClick(p0: Context?, p1: NotificationMessage?) {
        super.onInAppMessageClick(p0, p1)
        Log.e("$$$$$$", "点击了")
    }

}