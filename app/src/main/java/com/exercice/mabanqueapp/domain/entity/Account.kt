package com.exercice.mabanqueapp.domain.entity

data class Account(
    val balance: Double,
    val id: String,
    val label: String,
    val operations: List<Operation>
)