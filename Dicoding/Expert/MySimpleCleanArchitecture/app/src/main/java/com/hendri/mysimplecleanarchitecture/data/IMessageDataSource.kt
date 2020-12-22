package com.hendri.mysimplecleanarchitecture.data

import com.hendri.mysimplecleanarchitecture.domain.MessageEntity

interface IMessageDataSource {
    fun getMessageFromSource(name: String): MessageEntity
}