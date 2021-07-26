package com.ztzb.data.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : Fragment(), CoroutineScope by MainScope() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun dispose(vararg disposables: Disposable?) {
        disposables.forEach { disposable ->
            disposable?.takeUnless { it.isDisposed }?.dispose()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
