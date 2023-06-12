package com.exercice.mabanqueapp.ui.composables

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.exercice.mabanqueapp.R
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    Scaffold(
        content = {
            HostController(navController = navController)
        },
        bottomBar = {
            Column() {
                Divider(
                    color = colorResource(id = R.color.divider_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
                BottomBar(navController = navController)
            }

        }
    )
}