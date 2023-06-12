package com.exercice.mabanqueapp.di

import com.exercice.mabanqueapp.data.remote.MaBanqueApi
import com.exercice.mabanqueapp.data.repository.BankAccountRepositoryImpl
import com.exercice.mabanqueapp.domain.repository.BankAccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccountRepositoryImpl(
        maBanqueApi: MaBanqueApi
    ): BankAccountRepository {
        return BankAccountRepositoryImpl(maBanqueApi)
    }

    @Singleton
    @Provides
    fun provideDispatcherIo(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}