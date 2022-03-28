package com.example.fileplayground.show_video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.databinding.ActivityShowVideoBinding

class ShowVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}