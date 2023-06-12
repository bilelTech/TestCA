package com.exercice.mabanqueapp.ui.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.exercice.mabanqueapp.R

@Composable
fun BottomBar(navController: NavController) {
    fun onClick(route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { s ->
                popUpTo(s) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    BottomNavigation(backgroundColor = colorResource(id = R.color.bottom_bar_bg_color)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        listOf(ItemNav.account, ItemNav.simulation, ItemNav.profile).forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { onClick(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                label = { Text(text = item.name) },
                selectedContentColor = Color(0xFF2196F3),
                unselectedContentColor = Color(0xFF888888)
            )
        }
    }


}