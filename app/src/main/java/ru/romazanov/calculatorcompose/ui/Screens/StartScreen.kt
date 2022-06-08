package ru.romazanov.calculatorcompose.ui.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.romazanov.calculatorcompose.MainViewModel
import ru.romazanov.calculatorcompose.keys.Key
import ru.romazanov.calculatorcompose.ui.utils.DefaultButton


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartScreen(
    viewModel: MainViewModel
) {


    var text by viewModel.text

    val answer by viewModel.answer

    val list = viewModel.list.toList().joinToString(" ")

    val textList = listOf(
        Key.Delete,
        Key.Open,
        Key.Close,
        Key.Split,
        Key.Seven,
        Key.Eight,
        Key.Nine,
        Key.Multiply,
        Key.Four,
        Key.Five,
        Key.Six,
        Key.Minus,
        Key.One,
        Key.Two,
        Key.Three,
        Key.Plus,
        Key.Zero,
        Key.Zero2,
        Key.A,
        Key.Answer

    )

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(top = 24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            text = list,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            text = text,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.h2
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            text = answer,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.h1.copy(color = Color.LightGray)
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))

        Spacer(modifier = Modifier.padding(top = 24.dp))


        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            cells = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(textList) { item ->
                DefaultButton(text = item.name, onClick = { item.onClick(viewModel) })
            }
        }


    }

}




