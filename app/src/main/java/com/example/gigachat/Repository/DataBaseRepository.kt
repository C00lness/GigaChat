package com.example.gigachat.Repository

import com.example.gigachat.DataBase.AppDatabase
import com.example.gigachat.Model.Answer
import com.example.gigachat.Model.Choices
import com.example.gigachat.Model.DbAnswer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataBaseRepository @Inject constructor (private val db: AppDatabase) {
    suspend fun getAnswer(query:String) = db.answerDao().findByQuery(query)

    suspend fun insertAnswer(answer: DbAnswer) = db.answerDao().insertAnswer(answer)
}