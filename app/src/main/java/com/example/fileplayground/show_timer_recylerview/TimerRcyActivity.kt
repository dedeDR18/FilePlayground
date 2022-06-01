package com.example.fileplayground.show_timer_recylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ActivityTimerRcyBinding

class TimerRcyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerRcyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerRcyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}