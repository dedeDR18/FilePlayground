package com.example.fileplayground.show_video

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.fileplayground.databinding.ActivityShowVideoBinding

class ShowVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        openVideoFromGallery()

    }

    private fun openVideoFromGallery(){
        binding.frame.isVisible = false
        val pickVideoFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.frame.isVisible = true
            binding.videoView.setVideoURI(it)
            val mediaControl = MediaController(this)
            mediaControl.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaControl)
            share(it)
        }

        binding.openGallery.setOnClickListener {
            pickVideoFromGallery.launch("video/*")
        }
    }

    private fun share(uri: Uri?){
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "video/mp4"
                putExtra(Intent.EXTRA_STREAM, uri)
            }
            startActivity(Intent.createChooser(shareIntent,"share ke"))
        }
    }
}