package com.example.implicit_intent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat

class ChoosePictureActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private val REQUEST_IMAGE_CAPTURE = 2
    private lateinit var selectedImageUri: Uri
    private lateinit var imageView: ImageView
    private val REQUEST_CAMERA_PERMISSION = 1
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_picture)
        val intent = intent
        val message = intent.getStringExtra("Button")

        imageView = findViewById(R.id.image_view)

        // Set up a click listener for the "Choose from Gallery" button
        val chooseFromGalleryButton = findViewById<Button>(R.id.btn_gallery)
        chooseFromGalleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }

        // Set up a click listener for the "Take a Photo" button
        val takePhotoButton = findViewById<Button>(R.id.btn_camera)
        takePhotoButton.text =  message
        takePhotoButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    // Get the selected image's URI from the returned intent's data
                    selectedImageUri = data?.data ?: return
                    imageView.setImageURI(selectedImageUri)
                }
                REQUEST_IMAGE_CAPTURE -> {
                    // Get the captured image's URI from the returned intent's data
                    selectedImageUri = data?.extras?.get("data") as Uri
                    imageView.setImageURI(selectedImageUri)
                }
            }
        }
    }
}
