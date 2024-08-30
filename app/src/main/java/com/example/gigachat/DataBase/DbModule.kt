package com.example.gigachat.DataBase

import android.app.Application
import androidx.room.Room
import com.example.gigachat.Repository.ADataBase
import com.example.gigachat.Repository.RDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    //@Named("answer")
    @ADataBase
    fun getDataBase(app: Application) = Room.databaseBuilder(app, AnswerDataBase::class.java, "Answer.db").build()


    @Singleton
    @Provides
    //@Named("reg")
    @RDataBase
    fun getRegistrationDataBase(app: Application) = Room.databaseBuilder(app, RegistrationDataBase::class.java, "RegUser.db").build()
}