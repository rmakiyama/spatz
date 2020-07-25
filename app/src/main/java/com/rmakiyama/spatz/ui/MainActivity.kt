package com.rmakiyama.spatz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rmakiyama.spatz.R
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Spatz)
        super.onCreate(savedInstanceState)
        Insetter.setEdgeToEdgeSystemUiFlags(window.decorView, true)
    }
}
