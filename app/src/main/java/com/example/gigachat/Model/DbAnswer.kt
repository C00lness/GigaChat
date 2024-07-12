package com.example.gigachat.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbAnswer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val query: String,
    val answer: String
)
