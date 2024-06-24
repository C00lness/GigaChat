package com.example.gigachat.Model

data class Answer(
    val choices: Array<Choices>,
    val created: Long,
    val model: String,
    val aObject: String,
    val use: Usage
)
