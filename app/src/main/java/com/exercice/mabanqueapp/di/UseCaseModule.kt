package com.exercice.mabanqueapp.di

import com.exercice.mabanqueapp.domain.repository.BankAccountRepository
import com.exercice.mabanqueapp.domain.usecases.GetAccountDetailsUseCase
import com.exercice.mabanqueapp.domain.usecases.GetBanksAccountUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAccountDetailsUseCase(
        bankAccountRepository: BankAccountRepository
    ): GetAccountDetailsUseCase {
        return GetAccountDetailsUseCase(bankAccountRepository)
    }

    @Singleton
    @Provides
    fun provideGetBanksAccountUseCase(
        bankAccountRepository: BankAccountRepository
    ): GetBanksAccountUseCase {
        return GetBanksAccountUseCase(bankAccountRepository)
    }
}