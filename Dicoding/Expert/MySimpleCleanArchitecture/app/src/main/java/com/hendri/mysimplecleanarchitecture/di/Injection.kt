package com.hendri.mysimplecleanarchitecture.di

import com.hendri.mysimplecleanarchitecture.data.IMessageDataSource
import com.hendri.mysimplecleanarchitecture.data.MessageDataSource
import com.hendri.mysimplecleanarchitecture.data.MessageRepository
import com.hendri.mysimplecleanarchitecture.domain.IMessageRepository
import com.hendri.mysimplecleanarchitecture.domain.MessageInteractor
import com.hendri.mysimplecleanarchitecture.domain.MessageUseCase

object Injection {
    fun provideUseCase(): MessageUseCase {
        val messageRepository = provideRepository()
        return MessageInteractor(messageRepository)
    }

    private fun provideRepository(): IMessageRepository {
        val messageDataSource = provideDataSource()
        return MessageRepository(messageDataSource)
    }

    private fun provideDataSource(): IMessageDataSource {
        return MessageDataSource()
    }
}