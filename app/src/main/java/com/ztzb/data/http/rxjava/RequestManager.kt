package com.ztzb.data.http.rxjava

import com.ztzb.data.http.converter.ExceptionConvert
import com.ztzb.data.http.converter.ResponseConvert
import com.ztzb.data.http.observer.BaseObserver
import com.ztzb.data.http.response.BaseResponse
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object RequestManager {
    /**
     * 通用网络请求方法
     */
    fun <E> execute(
        observable: Observable<BaseResponse<E>>,
        observer: BaseObserver<E>
    ): Disposable {
        observable
            .map(ResponseConvert())
            .onErrorResumeNext(ExceptionConvert<E>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
        return observer.getDisposable()
    }

    /**
     * 通用耗时任务方法
     */
    fun <E> execute(listener: ExecuteListener<E>, observer: BaseObserver<E>): Disposable {
        Observable.create(ObservableOnSubscribe<E> { emitter ->
            emitter.onNext(listener.onExecute())
            emitter.onComplete()
        }).onErrorResumeNext(ExceptionConvert<E>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
        return observer.getDisposable()
    }

    interface ExecuteListener<E> {
        fun onExecute(): E
    }

}