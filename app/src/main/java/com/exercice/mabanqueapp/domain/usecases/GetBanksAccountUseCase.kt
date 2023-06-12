package com.exercice.mabanqueapp.domain.usecases

import com.exercice.mabanqueapp.domain.repository.BankAccountRepository

class GetBanksAccountUseCase(private val repository: BankAccountRepository) {
    suspend operator fun invoke() = repository.getBankAccounts()
}