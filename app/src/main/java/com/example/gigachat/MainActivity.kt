package com.example.gigachat

import com.example.gigachat.ViewModel.RegistrationViewModel
import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.gigachat.ViewModel.AnswerViewModel
import com.example.gigachat.ui.screen.About
import com.example.gigachat.ui.screen.HomeScreen
import com.example.gigachat.ui.screen.Registration
import com.example.gigachat.ui.screen.Settings
import com.example.gigachat.ui.theme.GigaChatTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GigaChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<AnswerViewModel>()
                    val regViewModel by viewModels<RegistrationViewModel>()
                    val navController = rememberNavController()

                    setContent @OptIn(ExperimentalMaterial3Api::class){
                        TopAppBar(title= { ClickableText(
                            text = AnnotatedString("GigaChat"),
                            onClick = {
                                navController.navigate(NavRoutes.Home.route)
                            },  style = TextStyle(
                                color = Color.Black,
                                fontSize = 26.sp,
                                fontFamily = FontFamily.Cursive
                            )
                        )},
                            navigationIcon={ IconButton(onClick = { }) {Icon(Icons.Filled.PlayArrow, contentDescription = "Параметры")} },
                            actions={
                                IconButton(onClick = { navController.navigate(NavRoutes.Registration.route)}) { Icon(Icons.Filled.Person, contentDescription = "Регистрация")}
                                IconButton(onClick = { navController.navigate(NavRoutes.Settings.route)}) { Icon(Icons.Filled.Settings, contentDescription = "Настройки")}
                                IconButton(onClick = { navController.navigate(NavRoutes.About.route)}) { Icon(Icons.Filled.Info, contentDescription = "О приложении")}
                            },
                            colors= TopAppBarDefaults.topAppBarColors(containerColor = Color.Unspecified,
                                titleContentColor = Color.LightGray,
                                navigationIconContentColor = Color.LightGray,
                                actionIconContentColor = Color.LightGray)
                        )

                        NavHost(navController, startDestination = NavRoutes.Home.route) {
                            composable(NavRoutes.Home.route) { HomeScreen(viewModel = viewModel)}
                            composable(NavRoutes.Settings.route) { Settings() }
                            composable(NavRoutes.About.route) { About() }
                            composable(NavRoutes.Registration.route) { Registration(regViewModel) }
                        }
                    }
                }
            }
        }
    }
}
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Settings : NavRoutes("settings")
    object About : NavRoutes("about")
    object Registration : NavRoutes("registration")
}

//navController.navigate(Navigation.NavRoutes.Home.route)
//visible = !visible
//