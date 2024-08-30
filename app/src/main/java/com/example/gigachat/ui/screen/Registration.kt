package com.example.gigachat.ui.screen

import com.example.gigachat.ViewModel.RegistrationViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Registration(regViewModel: RegistrationViewModel){
    var login by remember { mutableStateOf("") }
    var pswd by remember { mutableStateOf("") }
    var result = regViewModel.result
    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 70.dp, 16.dp, 16.dp),
        shape = RoundedCornerShape(16.dp),
        content = {

            Text("Registration", fontSize = 30.sp, color = MaterialTheme.colorScheme.primary, fontFamily = FontFamily.Cursive, modifier = Modifier
                .padding(16.dp))

                Row(modifier = Modifier.padding(16.dp))
                {
                    Text("Login", fontSize = 22.sp)
                }
                Row(modifier = Modifier.padding(16.dp))
                {
                    TextField(
                        login,
                        { login = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        leadingIcon = { Icon(Icons.Filled.Face, contentDescription = "Логин") }
                    )
                }
                Row(modifier = Modifier.padding(16.dp))
                {
                    Text("Password", fontSize = 22.sp)
                }
                Row(modifier = Modifier.padding(16.dp))
                {
                    TextField(
                        pswd,
                        { pswd = it },
                        textStyle = TextStyle(fontSize = 18.sp),
                        leadingIcon = { Icon(Icons.Filled.Check, contentDescription = "Пароль") }
                    )
                }
                Row(modifier = Modifier.padding(16.dp))
                {
                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Вход") },
                        text = { Text("Enter", fontFamily = FontFamily.Cursive, fontSize = 22.sp) },
                        onClick = {
                            regViewModel.getUser(login, pswd)
                        }
                    )
                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.PlayArrow, contentDescription = "Регистрация") },
                        text = {
                            Text(
                                "Registration",
                                fontFamily = FontFamily.Cursive,
                                fontSize = 22.sp
                            )
                        },
                        onClick = {
                            regViewModel.insertUser(login, pswd)
                        }
                    )
                }
            Text(result!!.toString(), modifier = Modifier
                .padding(16.dp, 8.dp)
                .verticalScroll(rememberScrollState()),
                color = MaterialTheme.colorScheme.primary)
        }
    )
}