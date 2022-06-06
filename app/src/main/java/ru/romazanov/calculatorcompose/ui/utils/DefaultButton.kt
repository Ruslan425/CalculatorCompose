package ru.romazanov.calculatorcompose.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    text: String,
    onClick:() -> Unit
) {
    Button(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp),
        onClick = onClick,
        shape = RoundedCornerShape(100)
    ) {
        Text(text)
    }
}