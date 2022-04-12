package com.example.fileplayground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.custom_video.CustomVideoActivity
import com.example.fileplayground.databinding.ActivityMainBinding
import com.example.fileplayground.recylerview.RecyclerviewActivity
import com.example.fileplayground.show_image.ShowImageActivity
import com.example.fileplayground.show_timer.TimerActivity
import com.example.fileplayground.show_video.ShowVideoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.showImage.setOnClickListener {
            startActivity(Intent(this, ShowImageActivity::class.java))
        }

        binding.showVideo.setOnClickListener {
            startActivity(Intent(this, ShowVideoActivity::class.java))
        }

        binding.showRecylerview.setOnClickListener {
            startActivity(Intent(this, RecyclerviewActivity::class.java))
        }

        binding.showCustomVideo.setOnClickListener {
            startActivity(Intent(this, CustomVideoActivity::class.java))
        }

        binding.showTimer.setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }
    }
}