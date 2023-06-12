package com.exercice.mabanqueapp.data.entity

data class GetAccountsResponseItem(
    val accounts: List<Account>,
    val isCA: Int,
    val name: String
)