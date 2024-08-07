package com.example.gigachat.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Answer(
    val choices: Array<Choices>,
    @PrimaryKey(autoGenerate = true)
    val created: Long,
    val model: String,
    val aObject: String,
    val use: Usage
)
