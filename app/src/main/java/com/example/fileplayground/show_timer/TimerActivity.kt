package com.example.fileplayground.show_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}