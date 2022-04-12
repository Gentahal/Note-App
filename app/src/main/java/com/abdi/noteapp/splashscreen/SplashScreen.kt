package com.abdi.noteapp.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abdi.noteapp.R
import com.abdi.noteapp.ui.MainActivity


class SplashScreen : AppCompatActivity() {

    private val durationSplashScreen: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        },durationSplashScreen)
        supportActionBar?.hide()
    }
}