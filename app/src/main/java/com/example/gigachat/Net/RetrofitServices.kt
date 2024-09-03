package com.example.gigachat.Net

import com.example.gigachat.Model.Answer
import com.example.gigachat.Model.DataRaw
import com.example.gigachat.Model.Token
import dagger.Provides
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url
import javax.inject.Singleton


interface RetrofitServices {
    @FormUrlEncoded
    @POST("oauth")
    suspend fun getToken(
        @Field("scope") scope: String = "GIGACHAT_API_PERS",
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded",
        @Header("Accept") accept: String = "application/json",
        @Header("RqUID") id: String = "Ваш ID",
        @Header("Authorization") auth: String = "Basic + ваш auth"
    ): Token

    @POST()
    suspend fun getAnswer(
        @Url url : String,
        @Body dataRaw: DataRaw,
        @Header("Authorization") auth: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Accept") accept: String = "application/json"): Answer
}
