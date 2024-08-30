package com.example.gigachat.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
    fun About(){
        Card (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 70.dp, 16.dp, 16.dp),
            shape = RoundedCornerShape(16.dp),
            content = {
                Row(){
                    Text("About Page", fontSize = 30.sp, color = MaterialTheme.colorScheme.primary, fontFamily = FontFamily.Cursive, modifier = Modifier
                        .padding(16.dp))
                }
                Row(modifier = Modifier.padding(16.dp))
                {
                    Text("Приложение может составить сбалансированную диету исходя из роста веса и пола или подобрать персональную систему тренировок."
                    ,color = MaterialTheme.colorScheme.primary, fontSize = 15.sp, modifier = Modifier.padding(16.dp))
                }

                Row(modifier = Modifier.padding(16.dp))
                {
                    Text("Необходимо выбрать свои параметры и нажать на кнопку Go. Приложение подбирает тренировки пока только для груди и ягодиц для незарегистрированных пользователей. "
                        ,color = MaterialTheme.colorScheme.primary, fontSize = 15.sp, modifier = Modifier.padding(16.dp))
                }

                Row(modifier = Modifier.padding(16.dp))
                {
                    Text("Разработано Исингалиевым Ермеком как пет проект. Все права защищены. 2024 год. Российская Федерация. Саратов."
                        ,color = MaterialTheme.colorScheme.primary, fontSize = 15.sp, modifier = Modifier.padding(16.dp))
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    Icon(Icons.Filled.Warning, contentDescription = "Информация о приложении")
                }
            }
        )
    }