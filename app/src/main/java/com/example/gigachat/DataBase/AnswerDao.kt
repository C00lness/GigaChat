package com.example.gigachat.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gigachat.Model.Choices
import com.example.gigachat.Model.DbAnswer
import io.reactivex.Completable

@Dao
interface AnswerDao {
    @Query("SELECT * FROM DbAnswer")
    suspend fun getAll(): List<DbAnswer>

    @Query("SELECT * FROM DbAnswer WHERE `query` LIKE :query LIMIT 1")
    suspend fun findByQuery(query: String): DbAnswer

    @Delete
    suspend fun delete(answer: DbAnswer)

    @Insert
    suspend fun insertAnswer(answer: DbAnswer)
}