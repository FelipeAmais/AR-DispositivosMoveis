package com.example.ardispositivosmoveis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handle = Handler(Looper.getMainLooper())
        handle.postDelayed({
            run();
        }, 3000)
    }

    fun run() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


