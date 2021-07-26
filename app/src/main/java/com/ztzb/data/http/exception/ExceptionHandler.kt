package com.ztzb.data.http.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import com.ztzb.data.http.common.Constants.ERROR_HTTP
import com.ztzb.data.http.common.Constants.ERROR_NETWORK
import com.ztzb.data.http.common.Constants.ERROR_PARSE
import com.ztzb.data.http.common.Constants.ERROR_SSL
import com.ztzb.data.http.common.Constants.ERROR_TIMEOUT
import com.ztzb.data.http.common.Constants.ERROR_UNKNOWN
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException

/**
 * 作者： Zun.
 * 创建： 2018/11/9.
 * 说明： 请求异常统一处理
 */
class ExceptionHandler {

    companion object {
        fun handler(e: Throwable): ResponseException {
            val responseException: ResponseException
            if (e is ApiException) {
                responseException = ResponseException(e, Integer.valueOf(e.code), e.msg)
            } else if (e is HttpException) {
                responseException = ResponseException(e, "$ERROR_HTTP:${e.code()}", "网络连接错误")
            } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                responseException = ResponseException(e, ERROR_PARSE, "解析出错")
            } else if (e is ConnectException) {
                responseException = ResponseException(e, ERROR_NETWORK, "连接失败")
            } else if (e is ConnectTimeoutException || e is java.net.SocketTimeoutException) {
                responseException = ResponseException(e, ERROR_TIMEOUT, "网络连接超时")
            } else if (e is javax.net.ssl.SSLHandshakeException) {
                responseException = ResponseException(e, ERROR_SSL, "证书验证失败")
            } else {
                responseException = ResponseException(e, ERROR_UNKNOWN, "未知错误")
            }
            return responseException
        }
    }
}