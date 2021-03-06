package com.example.fileplayground.show_image

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.fileplayground.databinding.ActivityShowImageBinding
import java.io.File

class ShowImageActivity : AppCompatActivity() {

    companion object{
        const val REQUEST_CODE = 101
        const val GALLERY_REQ_CODE = 99
    }

    private val TAG = "SHOWIMAGEACTIVITY"

    private lateinit var binding: ActivityShowImageBinding

    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowImageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        requestPermissions()

        takeImageFromCamera()

        pickImageFromGallery()
    }


    private fun takeImageFromCamera(){

        val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()){
            if (it){
                Log.d(TAG, "uri image = $imageUri")
                binding.imageView.setImageURI(imageUri)
            }
        }

        binding.btnCamera.setOnClickListener {

            val photoFile = File.createTempFile(
                "IMG_",
                ".jpg",
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            )

            imageUri = FileProvider.getUriForFile(
                this,
                "${packageName}.provider",
                photoFile
            )

            takePicture.launch(imageUri)
        }
    }

    private fun pickImageFromGallery(){
        val pickFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()){
            binding.imageView.setImageURI(it)
        }

        binding.btnGalery.setOnClickListener {
            pickFromGallery.launch("image/*")
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(){
        var permissionToRequest = mutableListOf<String>()
        if (!hasCameraPermission()){
            permissionToRequest.add(Manifest.permission.CAMERA)
        }

        if (permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(), REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (REQUEST_CODE == 101 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d(TAG, "${permissions[i]} granted..")
                }
            }
        }
    }
}
