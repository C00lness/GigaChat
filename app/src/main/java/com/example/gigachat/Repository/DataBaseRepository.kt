package com.example.gigachat.Repository

import com.example.gigachat.DataBase.AnswerDataBase
import com.example.gigachat.DataBase.RegistrationDataBase
import com.example.gigachat.Model.DbAnswer
import com.example.gigachat.Model.RegUser
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class DataBaseRepository @Inject constructor (@ADataBase private val db: AnswerDataBase) {
    suspend fun getAnswer(query:String) = db.answerDao().findByQuery(query)

    suspend fun insertAnswer(answer: DbAnswer) = db.answerDao().insertAnswer(answer)
}

@Singleton
class RegistrationRepository @Inject constructor (@RDataBase private val db: RegistrationDataBase) {
    suspend fun checkUser(login:String, pswd: String) = db.registrationDao().findByLoginAndPassword(login, pswd)
    suspend fun insertUser(user: RegUser) = db.registrationDao().insertUser(user)
    suspend fun getUser(login:String) = db.registrationDao().findByLogin(login)
}