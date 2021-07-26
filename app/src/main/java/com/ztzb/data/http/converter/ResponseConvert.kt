package com.ztzb.data.http.converter

import com.ztzb.data.http.exception.ApiException
import com.ztzb.data.http.response.BaseResponse
import io.reactivex.functions.Function

class ResponseConvert<E> : Function<BaseResponse<E>, E> {
    override fun apply(baseResponse: BaseResponse<E>): E {
        if (BaseResponse.SUCCESS != baseResponse.code) {
            throw ApiException(baseResponse.code, baseResponse.msg)
        }
        if (baseResponse.data == null) {
            baseResponse.data = "" as E
        }
        return baseResponse.data
    }
}