package com.example.fileplayground.custom_video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ActivityCustomVideoBinding

class CustomVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}