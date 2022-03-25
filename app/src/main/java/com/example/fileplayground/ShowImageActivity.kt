package com.example.fileplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.databinding.ActivityShowImageBinding

class ShowImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}