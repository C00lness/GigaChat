package com.example.gigachat

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object Utils {
    @SuppressLint("NewApi")
    fun needToken(): Boolean {
        val context = MyApplication.appContext
        val sharedpref =
            context.getSharedPreferences(
                "user details",
                AppCompatActivity.MODE_PRIVATE
            )

        val timeToStartToken = sharedpref?.getString("timeToStartToken", null)
        if (timeToStartToken == null)
            return true

        val today = LocalDateTime.now()
        val startToken = LocalDateTime.parse(timeToStartToken)

        val duration = ChronoUnit.MINUTES.between(startToken, today)
        if (duration < 30)
            return false
        else
            return true
    }
}