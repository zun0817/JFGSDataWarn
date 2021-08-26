package com.ztzb.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.ztzb.data.di.appModule
import com.ztzb.data.util.HttpTools
import com.ztzb.data.util.ToastManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import kotlin.properties.Delegates

/**
 * 作者： Zun.
 * 创建： 2019/4/15.
 * 说明： 全局
 */
class App : Application(), Thread.UncaughtExceptionHandler {

    companion object {
        private val TAG = App::class.java.simpleName
        var application: Application by Delegates.notNull()
        var instance: HttpTools by Delegates.notNull()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        instance = HttpTools()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        Thread.setDefaultUncaughtExceptionHandler(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(application)
            modules(appModule)
        }
        ToastManager.init(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e("uncaughtException", "error", e)
    }
}
