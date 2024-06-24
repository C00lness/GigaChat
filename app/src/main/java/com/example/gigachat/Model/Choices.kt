package com.example.gigachat.Model

data class Choices(
    val message: Messages,
    val index: Int,
    val finish_reason: String
)
