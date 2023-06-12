package com.exercice.mabanqueapp.data.repository

import com.exercice.mabanqueapp.data.remote.MaBanqueApi
import com.exercice.mabanqueapp.domain.entity.Account
import com.exercice.mabanqueapp.domain.entity.BankAccount
import com.exercice.mabanqueapp.domain.entity.Operation
import com.exercice.mabanqueapp.domain.repository.BankAccountRepository
import com.exercice.mabanqueapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BankAccountRepositoryImpl constructor(
    private val maBanqueApi: MaBanqueApi
) : BankAccountRepository {

    override fun getAccount(id: String): Flow<Resource<Account>> = flow {
        emit(Resource.loading(null))
        try {
            val result = maBanqueApi.getBankAccounts()
            if (result.isSuccessful) {
                result.body()?.map {
                    val accounts = it.accounts.filter { it.id == id }.map { account ->
                        Account(account.balance,
                            account.id,
                            account.label,
                            account.operations.map { operation ->
                                Operation(operation.amount, operation.date, operation.title)
                            })
                    }
                    if (accounts.isNotEmpty()) {
                        emit(Resource.success(accounts[0]))
                    } else {
                        emit(Resource.error(null, "error"))
                    }
                }
            } else {
                emit(Resource.error(null, "error"))
            }
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: ""))
        }

    }

    override fun getBankAccounts(): Flow<Resource<List<BankAccount>>> = flow {
        emit(Resource.loading(null))
        try {
            val result = maBanqueApi.getBankAccounts()
            if (result.isSuccessful) {
                val response = result.body()?.map {
                    val balance = it.accounts.map { it.balance }.sum()
                    BankAccount(it.isCA, it.name, balance, it.accounts.map { account ->
                        Account(
                            account.balance,
                            account.id,
                            account.label,
                            account.operations.map { operation ->
                                Operation(operation.amount, operation.date, operation.title)
                            })
                    })
                }
                emit(Resource.success(response))
            } else {
                emit(Resource.error(null, "error"))
            }
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: ""))
        }
    }

    override fun getOperationByAccountId(accountId: String): Flow<Resource<List<Operation>>> =
        flow {
            emit(Resource.loading(null))
            try {
                val result = maBanqueApi.getBankAccounts()
                result.body()?.map {
                    val opers = ArrayList<Operation>()
                    it.accounts.filter { account -> account.id == accountId }.map { res ->
                        res.operations.map { operation ->
                            opers.add(Operation(operation.amount, operation.date, operation.title))
                        }
                    }
                    emit(Resource.success(opers))
                }
            } catch (exception: Exception) {
                emit(Resource.error(null, exception.message ?: ""))
            }
        }
}