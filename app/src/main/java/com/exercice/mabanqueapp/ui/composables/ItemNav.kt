package com.exercice.mabanqueapp.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.exercice.mabanqueapp.domain.entity.Account
import com.google.gson.Gson

sealed class ItemNav(
    val name: String,
    val icon: ImageVector,
    val route: String
) {

    object account : ItemNav("Mes Comptes", Icons.Default.Favorite, "Account")
    object simulation : ItemNav("Simulation", Icons.Default.Favorite, "Simulation")
    object profile :
        ItemNav("à vous de jouer", Icons.Default.Favorite, "à vous de jouer")

    object accountDetails :
        ItemNav("Mes Comptes", Icons.Default.Favorite, "AccountDetails/{accountId}") {
        const val ARG_ACCOUNT = "accountId"
        fun route(model: Account): String {
            return "AccountDetails/${model.id}"
        }
    }
}
