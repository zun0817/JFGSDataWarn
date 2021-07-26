package com.ztzb.data.http.rxjava

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.FlowableSubscribeProxy
import com.uber.autodispose.ObservableSubscribeProxy
import com.uber.autodispose.SingleSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.lifecycle.autoDisposable
import com.ztzb.data.http.converter.ObservableExceptionConvert
import com.ztzb.data.http.converter.ResponseConvert
import com.ztzb.data.http.converter.SingleExceptionConvert
import com.ztzb.data.http.response.BaseResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun <E> Single<BaseResponse<E>>.async(): Single<E> =
    this.map(ResponseConvert())
        .onErrorResumeNext(SingleExceptionConvert<E>())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <E> Observable<BaseResponse<E>>.async(): Observable<E> =
    this.map(ResponseConvert())
        .onErrorResumeNext(ObservableExceptionConvert<E>())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <E> Single<E>.polling(delay: Long, unit: TimeUnit): Flowable<E> =
    this.repeatWhen { it.delay(delay, unit) } //请求成功延迟后重新发送请求,最后subscribe()不会回调onComplete
        .retryWhen { it.delay(delay, unit) } //请求失败延迟后重新发送请求,最后subscribe()不会回调onError
        .onBackpressureDrop()

fun <T> Single<T>.disposableOnDestroy(owner: LifecycleOwner): SingleSubscribeProxy<T> {
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
}

fun <T> Observable<T>.disposableOnDestroy(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
}

fun <T> Observable<T>.disposableOnPause(owner: LifecycleOwner): ObservableSubscribeProxy<T> {
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_PAUSE))
}

fun <T> Flowable<T>.disposableOnDestroy(owner: LifecycleOwner): FlowableSubscribeProxy<T> {
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
}

fun <T> Flowable<T>.disposableOnPause(owner: LifecycleOwner): FlowableSubscribeProxy<T> {
    return this.autoDisposable(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_PAUSE))
}