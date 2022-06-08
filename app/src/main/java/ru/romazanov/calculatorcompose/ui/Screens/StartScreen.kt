package ru.romazanov.calculatorcompose.ui.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.romazanov.calculatorcompose.MainViewModel
import ru.romazanov.calculatorcompose.ui.elements.DefaultButton
import ru.romazanov.calculatorcompose.constants.KEYS_LIST


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartScreen(
    viewModel: MainViewModel,
) {

    val text by viewModel.text
    val answer by viewModel.answer
    val list = viewModel.list.toList().joinToString(" ")
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                    Text(
                        text = "Калькулятор",
                        style = MaterialTheme.typography.h5
                    )
                }
            }
        },
        drawerElevation = 4.dp,
        drawerContent = {
            LazyColumn(modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()) {

            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                text = list,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h5.copy(color = Color.LightGray),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis

            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                text = text,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                text = answer,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
            LazyVerticalGrid(
                cells = GridCells.Fixed(4),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(KEYS_LIST) { item ->
                    DefaultButton(text = item.name, onClick = { item.onClick(viewModel) })
                }
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val viewModel = MainViewModel()
    StartScreen(viewModel = viewModel)
}



