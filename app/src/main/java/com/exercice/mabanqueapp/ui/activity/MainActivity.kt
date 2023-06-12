package com.exercice.mabanqueapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.exercice.mabanqueapp.ui.composables.MainScaffold
import com.exercice.mabanqueapp.ui.theme.MaBanqueAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaBanqueAppTheme {
                // A surface container using the 'background' color from the theme
                MainScaffold()
            }
        }
    }
}