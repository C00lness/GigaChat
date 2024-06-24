package com.example.gigachat

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext
    }
    companion object {

        lateinit  var appContext: Context

    }
}