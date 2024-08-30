package com.example.gigachat.ViewModel

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gigachat.Model.DataRaw
import com.example.gigachat.Model.DbAnswer
import com.example.gigachat.Model.Messages
import com.example.gigachat.MyApplication
import com.example.gigachat.Repository.ADataBase
import com.example.gigachat.Repository.DataBaseRepository
import com.example.gigachat.Repository.MainRepository
import com.example.gigachat.Utils

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel

class AnswerViewModel @Inject constructor(val repository: MainRepository, val dbRepository: DataBaseRepository): ViewModel(){
    var result: String  by mutableStateOf("")
    val context = MyApplication.appContext
    val sharedpref =
        context.getSharedPreferences(
            "user details",
            AppCompatActivity.MODE_PRIVATE
        )

    @RequiresApi(Build.VERSION_CODES.O)
    fun getToken(){
        viewModelScope.launch {
            try {
                val token = repository.getToken().access_token
                val editor = sharedpref.edit()
                editor.putString("token", token)
                editor.putString("timeToStartToken", LocalDateTime.now().toString())
                editor.apply()
                Log.d("ViewModel", "getToken " +token)
            }
            catch (E:IOException){result = "getToken " + E.message.toString()}
            catch (E:HttpException){result = "getToken " + E.message.toString()}
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getAnswer(content: String)
    {
        val token = sharedpref.getString("token", null)?:""
        Log.d("ViewModel", "getAnswer " + token)
        val msg = Messages("user", content)
        val dr = DataRaw("GigaChat", arrayOf( msg), 1, 0.1, 1, false, 512, 1, 0)

            viewModelScope.launch {
                try {
                    val nAnswer: DbAnswer? = dbRepository.getAnswer(content)
                    if ( nAnswer != null)
                    {
                        Log.d("Mode", "Db")
                        result = nAnswer.answer
                    }
                    else
                    {
                        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val currentDate = sdf.format(Date())

                        result = repository.getAnswer("https://gigachat.devices.sberbank.ru/api/v1/chat/completions", dr, "Bearer " + token).choices[0].message.content
                        val dbAnswer = DbAnswer(currentDate.hashCode().toInt(), content, result)
                        dbRepository.insertAnswer(dbAnswer)
                        Log.d("Mode", "Net")
                    }
                }
                catch (E:IOException){result = "getAnswer " + E.message.toString()}
                catch (E:HttpException){result = "getAnswer " + E.message.toString()}
            }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getResult(content: String)
    {
        if (Utils.needToken())
        {
            viewModelScope.launch {
                try {
                    val token = repository.getToken().access_token
                    val editor = sharedpref.edit()
                    editor.putString("token", token)
                    editor.putString("timeToStartToken", LocalDateTime.now().toString())
                    editor.apply()
                    Log.d("ViewModel", "getToken " +token)
                    getAnswer(content)
                }
                catch (E:IOException){result = "getToken " + E.message.toString()}
                catch (E:HttpException){result = "getToken " + E.message.toString()}
            }
        }
        else
            getAnswer(content)
    }
}