package ru.romazanov.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import ru.romazanov.calculatorcompose.ui.Screens.StartScreen
import ru.romazanov.calculatorcompose.ui.theme.CalculatorComposeTheme
import ru.romazanov.calculatorcompose.constants.VIEW_MODEL


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CalculatorComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StartScreen(viewModel = VIEW_MODEL)
                }
            }
        }
    }
}

