package com.example.gigachat.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gigachat.Model.DbAnswer

@Database(entities = [DbAnswer::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun answerDao(): AnswerDao
}