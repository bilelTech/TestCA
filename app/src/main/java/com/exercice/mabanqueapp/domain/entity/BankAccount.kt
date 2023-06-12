package com.exercice.mabanqueapp.domain.entity

data class BankAccount(
    val isCA: Int,
    val name: String,
    val balance: Double,
    val accounts: List<Account>
)