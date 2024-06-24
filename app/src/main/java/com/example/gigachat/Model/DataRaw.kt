package com.example.gigachat.Model


data class DataRaw(
    val model: String,
    val messages: Array<Messages>,
    val temperature: Int,
    val top_p: Double,
    val n: Int,
    val stream: Boolean,
    val max_tokens: Int,
    val repetition_penalty: Int,
    val update_interval: Int
)

//"temperature": 1,
//"top_p": 0.1,
//"n": 1,
//"stream": false,
//"max_tokens": 512,
//"repetition_penalty": 1,
//"update_interval": 0
