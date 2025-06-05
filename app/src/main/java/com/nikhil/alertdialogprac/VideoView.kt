package com.nikhil.alertdialogprac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityVideoViewBinding

class VideoView : AppCompatActivity() {
    private val binding: ActivityVideoViewBinding by lazy {
        ActivityVideoViewBinding.inflate(layoutInflater)
    }
    private val pickVideo = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val videoUri: Uri? = result.data?.data
            println("VideoUri : $videoUri")
            if (videoUri != null) {
                playVideo(videoUri)
            }else{
                Toast.makeText(this, "Video Uri is null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.pickvideo.setOnClickListener {
            pickVideoFromGallery()
        }
    }

    private fun pickVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        pickVideo.launch(intent)
    }

    private fun playVideo(videoUri: Uri) {
        try {

            val mediaController = MediaController(this)
            binding.video.setMediaController(mediaController)
            mediaController.setAnchorView(binding.video)

            binding.video.setVideoURI(videoUri)
            binding.video.requestFocus()
            binding.video.start()

            binding.video.setOnErrorListener { mp, what, extra ->
                Toast.makeText(this, "Video playback error.", Toast.LENGTH_SHORT).show()
                true
            }
        }
            catch(e: Exception) {
                Toast.makeText(this, "Invalid video URI.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }


