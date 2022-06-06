package ru.romazanov.calculatorcompose.ui.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.romazanov.calculatorcompose.MainViewModel
import ru.romazanov.calculatorcompose.ui.utils.DefaultButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartScreen(
    viewModel: MainViewModel
) {

    val list = (1..9).map { it.toString() }

    var text by viewModel.text

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
        Spacer(modifier = Modifier.padding(top = 24.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it })

        Spacer(modifier = Modifier.padding(top = 24.dp))

        Row() {
            LazyVerticalGrid(
                modifier = Modifier.width(300.dp),
                cells = GridCells.Fixed(3),
              ) {

                items(list.size) { item ->
                    DefaultButton(
                        text = list[item],
                        onClick = {text += list[item]}
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                DefaultButton(
                    text = "÷",
                    onClick = {text += "/"}
                )
                DefaultButton(
                    text = "*",
                    onClick = {text += "*"}
                )
                DefaultButton(
                    text = "-",
                    onClick = {text += "-"}
                )
                DefaultButton(
                    text = "+",
                    onClick = {text += "+"}
                )

            }

        }





        Spacer(modifier = Modifier.padding(top = 24.dp))

        Button(
            enabled = text.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                text = viewModel.getSum(text)
        }) {
            Text("Ответ")
        }


    }
}