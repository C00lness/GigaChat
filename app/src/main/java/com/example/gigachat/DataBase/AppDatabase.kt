package com.example.gigachat.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gigachat.Model.DbAnswer
import com.example.gigachat.Model.RegUser
import javax.inject.Named
import javax.inject.Qualifier


@Database(entities = [DbAnswer::class], version = 1)
abstract class AnswerDataBase: RoomDatabase(){
    abstract fun answerDao(): AnswerDao
}
@Database(entities = [RegUser::class], version = 1)
abstract class RegistrationDataBase: RoomDatabase(){
    abstract fun registrationDao(): RegistrationDao
}