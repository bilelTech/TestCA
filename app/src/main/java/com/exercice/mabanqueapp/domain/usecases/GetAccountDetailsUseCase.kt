package com.exercice.mabanqueapp.domain.usecases

import com.exercice.mabanqueapp.domain.repository.BankAccountRepository

class GetAccountDetailsUseCase(private val repository: BankAccountRepository) {
    suspend operator fun invoke(accountId: String) = repository.getAccount(accountId)
}