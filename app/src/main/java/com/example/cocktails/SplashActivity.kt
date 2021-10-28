package com.example.cocktails

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    val DELAY_TIME_SEC = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delayForStart()
    }

    private fun delayForStart() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                delay(DELAY_TIME_SEC * 1000L)
                val myIntent = Intent(applicationContext, MainActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}