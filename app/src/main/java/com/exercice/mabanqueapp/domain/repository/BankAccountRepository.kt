package com.exercice.mabanqueapp.domain.repository

import com.exercice.mabanqueapp.domain.entity.Account
import com.exercice.mabanqueapp.domain.entity.BankAccount
import com.exercice.mabanqueapp.domain.entity.Operation
import com.exercice.mabanqueapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BankAccountRepository {

    fun getBankAccounts(): Flow<Resource<List<BankAccount>>>

    fun getAccount(id: String): Flow<Resource<Account>>

    fun getOperationByAccountId(accountId: String): Flow<Resource<List<Operation>>>
}