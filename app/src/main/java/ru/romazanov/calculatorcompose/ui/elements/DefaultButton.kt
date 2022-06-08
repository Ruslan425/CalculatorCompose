package ru.romazanov.calculatorcompose.ui.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize(),
        onClick = onClick,
        elevation = 4.dp
    ) {
        Text(
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
            text = text,
            textAlign = TextAlign.Center
        )

    }
}