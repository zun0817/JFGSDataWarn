package com.ztzb.data.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ztzb.data.common.AppManager
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 作者： Zun.
 * 创建： 2019/4/15.
 * 说明： Activity基类
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        AppManager.getInstance().addActivity(this)
    }

    fun dispose(vararg disposables: Disposable?) {
        disposables.forEach { disposable ->
            disposable?.takeUnless { it.isDisposed }?.dispose()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        AppManager.getInstance().finishActivity(this)
        cancel()
    }

}
