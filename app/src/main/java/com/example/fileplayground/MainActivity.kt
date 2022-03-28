package com.example.fileplayground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fileplayground.databinding.ActivityMainBinding
import com.example.fileplayground.show_image.ShowImageActivity

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
    }
}