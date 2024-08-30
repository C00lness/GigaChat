package com.example.gigachat.Repository

import com.example.gigachat.Model.DataRaw
import com.example.gigachat.Net.RetrofitServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val service : RetrofitServices) {
    suspend fun getToken() = service.getToken()
    suspend fun getAnswer(url : String, dataRaw: DataRaw, token:String) = service.getAnswer(url, dataRaw, token)
}