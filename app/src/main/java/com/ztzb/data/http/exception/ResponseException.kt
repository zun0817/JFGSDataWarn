package com.ztzb.data.http.exception

/**
 * 作者： Zun.
 * 创建： 2018/11/9.
 * 说明： 请求异常基础信息类
 */
class ResponseException : Exception {
    private var errorCode: String
    private var errorMessage: String?

    constructor(throwable: Throwable, errorCode: Int, errorMessage: String?) : super(throwable) {
        this.errorCode = errorCode.toString()
        this.errorMessage = errorMessage
    }

    constructor(throwable: Throwable, errorCode: String, errorMessage: String?) : super(throwable) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage
    }

    fun getErrorCode(): String {
        return errorCode
    }

    fun setErrorCode(errorCode: String) {
        this.errorCode = errorCode
    }

    fun getErrorMessage(): String? {
        return errorMessage
    }

    fun setErrorMessage(errorMessage: String) {
        this.errorMessage = errorMessage
    }

    override fun toString(): String {
        return "[$errorCode, $errorMessage]"
    }
}