package com.ztzb.data.http.converter

import com.ztzb.data.http.exception.ExceptionHandler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Function

class SingleExceptionConvert<E> : Function<Throwable, SingleSource<out E>> {
    override fun apply(throwable: Throwable): SingleSource<out E> {
        return Single.error(ExceptionHandler.handler(throwable))
    }
}