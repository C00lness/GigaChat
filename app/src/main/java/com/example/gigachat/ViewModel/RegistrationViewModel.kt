package com.example.gigachat.ViewModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gigachat.Model.RegUser
import com.example.gigachat.Repository.RDataBase

import com.example.gigachat.Repository.RegistrationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RegistrationViewModel @Inject constructor( val dbRepository: RegistrationRepository): ViewModel(){
    var result: String  by mutableStateOf("")
    fun insertUser(login:String, pswd: String){
        viewModelScope.launch {
            if (login == "") result = "Login не может быть пустым"
            else
            {
                if (pswd == "") result = "Password не может быть пустым"
                else
                {
                    var user: RegUser? = dbRepository.getUser(login)
                    if ( user == null)
                    {
                        user = RegUser(0, login, pswd, "", "")
                        dbRepository.insertUser(user)
                        result = "Успешная регистрация"
                    }
                    else result = "Пользователь с таким логином уже существует"
                }
            }
        }
    }

    fun getUser(login:String, pswd: String){
        viewModelScope.launch {
            if (login == "") result = "Login не может быть пустым"
            else
            {
                if (pswd == "") result = "Password не может быть пустым"
                else
                {
                    var user: RegUser? = dbRepository.checkUser(login, pswd)
                    if (user != null) result = "Вход выполнен"
                    else result = "Пользователь с таким логином\\паролем не обнаружен"
                }
            }
        }
    }
}
