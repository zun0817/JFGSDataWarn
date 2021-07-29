package com.ztzb.data.model.repository

import android.util.Log
import com.ztzb.data.base.BaseRepository
import com.ztzb.data.http.rxjava.async
import com.ztzb.data.model.data.MessageBean
import com.ztzb.data.model.remote.MessageService
import io.reactivex.Single

class MessageRepository(private val messageService: MessageService) : BaseRepository() {

    private val TAG = MessageRepository::class.java.simpleName

    fun getMessageParam(pageNumber: Int, pageSize: Int): MutableMap<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["pageNumber"] = pageNumber
        map["pageSize"] = pageSize
        return map
    }

    fun requestOfMessage(map: MutableMap<String, Any>): Single<MessageBean> {
        val request = messageService.requestOfMessage(map)
        return request.async()
            .doOnSuccess { Log.i(TAG, "onSuccess: $it") }
            .doOnError { Log.e(TAG, "onError: ", it) }
    }

}