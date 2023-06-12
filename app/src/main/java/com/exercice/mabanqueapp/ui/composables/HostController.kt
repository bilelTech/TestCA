package com.exercice.mabanqueapp.ui.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exercice.mabanqueapp.AccountDetailScreen
import com.exercice.mabanqueapp.SelectedScreen
import com.exercice.mabanqueapp.domain.entity.Account
import com.google.gson.Gson
import com.exercice.mabanqueapp.AccountScreen as HomeScreen

@Composable
fun HostController(
    navController: NavHostController
) {

    NavHost(navController, startDestination = ItemNav.account.route) {
        composable(ItemNav.account.route) {
            HomeScreen(accountViewModel = hiltViewModel(), goToAccountDetails = {
                navController.navigate(ItemNav.accountDetails.route(it))
            })

        }
        composable(
            route = ItemNav.accountDetails.route,
        ) {
            val accountId = it.arguments?.getString(ItemNav.accountDetails.ARG_ACCOUNT)
            AccountDetailScreen(
                accountDetailsViewModel = hiltViewModel(),
                accountId = accountId ?: "",
                onBackPressed = { navController.navigateUp() }
            )
        }
        composable(ItemNav.simulation.route) {
            SelectedScreen(ItemNav.simulation.route)
        }
        composable(ItemNav.profile.route) {
            SelectedScreen(ItemNav.profile.route)
        }
    }
}

