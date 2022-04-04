package com.example.fileplayground.show_video

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.MediaController
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.example.fileplayground.databinding.ActivityShowVideoBinding
import com.example.fileplayground.show_image.ShowImageActivity
import java.io.File

class ShowVideoActivity : AppCompatActivity() {

    private val TAG = "SHOWVIDEOACTIVITY"

    private lateinit var binding: ActivityShowVideoBinding

    private var videoUri: Uri? = null

    private var uri = MutableLiveData<Uri>()

    companion object {
        const val CAPTURE_VIDEO_REQUEST_CODE = 99
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowVideoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions()

        openVideoFromGallery()
        pickVideoFromCamera()

    }

    private fun openVideoFromGallery() {
        binding.frame.isVisible = false
        val pickVideoFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) {
            binding.frame.isVisible = true
            Log.d(TAG, "uri video gallery = $it")
            binding.videoView.setVideoURI(it)
            setMediaControl()
            share(it)
        }

        binding.openGallery.setOnClickListener {
            pickVideoFromGallery.launch("video/*")
        }
    }

    private fun share(uri: Uri?) {
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "video/mp4"
                putExtra(Intent.EXTRA_STREAM, uri)
            }
            startActivity(Intent.createChooser(shareIntent, "share ke"))
        }
    }


    private fun pickVideoFromCamera() {

        val takeVideo = registerForActivityResult(ActivityResultContracts.CaptureVideo()) {
            if (it) {
                binding.frame.isVisible = true
                binding.videoView.setVideoURI(videoUri)
                setMediaControl()
            }
        }

        binding.videoFromCamera.setOnClickListener {

            val videoFile = File.createTempFile(
                "VUD_",
                ".mp4",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )

            videoUri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                videoFile
            )

            takeVideo.launch(videoUri)
        }

    }

    private fun setMediaControl(){
        val mediaControl = MediaController(this)
        mediaControl.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaControl)
    }

    private fun hasCameraPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasWriteExternalStoregePermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        var permissionToRequest = mutableListOf<String>()
        if (!hasCameraPermission()) {
            permissionToRequest.add(Manifest.permission.CAMERA)
        }
        if (!hasWriteExternalStoregePermission()) {
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this, permissionToRequest.toTypedArray(),
                ShowImageActivity.REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (CAPTURE_VIDEO_REQUEST_CODE == 99 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "${permissions[i]} granted..")
                }
            }
        }
    }
}