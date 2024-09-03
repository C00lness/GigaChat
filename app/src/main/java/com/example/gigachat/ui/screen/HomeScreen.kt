package com.example.gigachat.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gigachat.Utils

import com.example.gigachat.ViewModel.AnswerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen (viewModel: AnswerViewModel){
    val sexOptions = listOf("Мужчина", "Женщина")
    var selectedSexOption by remember { mutableStateOf(sexOptions[0]) }

    val resultOptions = listOf("Похудеть", "Накачаться")
    var selectedResultOption by remember { mutableStateOf(resultOptions[0]) }

    var result = viewModel.result
    var textWeight by rememberSaveable { mutableStateOf("") }
    var textLenght by rememberSaveable { mutableStateOf("") }

    var visible by remember { mutableStateOf(true) }

        Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 70.dp, 16.dp, 16.dp),
        shape = RoundedCornerShape(16.dp),
        content = {
            Row(Modifier.padding(5.dp))
            {
                IconButton(onClick = {
                    visible = !visible
                }) {
                    Icon(
                        if (visible) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Скрыть\\Показать"
                    )
                }
            }
            Row(
                Modifier
                    .alpha(if (visible) 0f else 1f)
            )
            {
                Text(
                    (if (visible) "" else result!!),
                    modifier = Modifier
                        .padding(8.dp, 2.dp)
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = (if (visible) 0.sp else 14.sp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Column(
                    Modifier
                        .alpha(if (visible) 1f else 0f)
                )
                {
                    sexOptions.forEach { option ->
                        Row(
                            Modifier
                                .padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == selectedSexOption),
                                onClick = { selectedSexOption = option },
                            )
                            Text(text = option, modifier = Modifier.padding(2.dp), fontSize = 14.sp)
                        }
                    }
                }
                Column(
                    Modifier
                        .alpha(if (visible) 1f else 0f)
                )
                {
                    resultOptions.forEach { option ->
                        Row(
                            Modifier
                                .padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == selectedResultOption),
                                onClick = { selectedResultOption = option })
                            Text(text = option, modifier = Modifier.padding(2.dp), fontSize = 14.sp)
                        }
                    }
                }
            }
            Row(Modifier.fillMaxWidth().alpha(if (visible) 1f else 0f), horizontalArrangement = Arrangement.Center) {
                TextField(
                    value = textWeight, onValueChange = { textWeight = it },
                    label = { Text("Вес (кг)", fontSize = 14.sp) },
                    modifier = Modifier.padding(16.dp, 2.dp)
                )
            }

            Row(Modifier.fillMaxWidth().alpha(if (visible) 1f else 0f), horizontalArrangement = Arrangement.Center) {
                TextField(
                    value = textLenght, onValueChange = { textLenght = it },
                    label = { Text("Рост (см)", fontSize = 14.sp) },
                    modifier = Modifier.padding(16.dp, 2.dp)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .alpha(if (visible) 1f else 0f),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Добавить") },
                    text = { Text("Go", fontFamily = FontFamily.Cursive, fontSize = 22.sp) },
                    onClick = {
                        var finContent: String
                        var content: String = selectedSexOption.toString() + " "
                        if (selectedResultOption.toString() == "Накачаться") {
                            content = content + "Хочу накачать мышцы "
                            finContent =
                                "Составь примерную программу тренировок на грудь и ягодицы."
                        } else {
                            content = content + "Хочу похудеть "
                            finContent = "Составь пожалуйста меню на день."
                        }
                        content =
                            content + textWeight.toString() + " килограмм. " + textLenght.toString() + " сантиметров. " + finContent
                        viewModel.getResult(content)
                    }
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    result!!,
                    modifier = Modifier
                        .alpha(if (visible) 1f else 0f)
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

    )
}