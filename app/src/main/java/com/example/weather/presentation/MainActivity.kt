package com.example.weather.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R
import dagger.hilt.android.AndroidEntryPoint

const val LOCATION_PERMISSION_REQUEST_CODE = 1122

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}