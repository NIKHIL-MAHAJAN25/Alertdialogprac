package com.nikhil.alertdialogprac

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityVideo2Binding
import com.nikhil.alertdialogprac.databinding.ActivityVideoViewBinding

class VideoActivity2 : AppCompatActivity() {
    private val binding: ActivityVideo2Binding by lazy {
        ActivityVideo2Binding.inflate(layoutInflater)
    }
    lateinit var mediaControls: MediaController
    val videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mediaControls = MediaController(this)
        mediaControls.setAnchorView(this.binding.video2)
        binding.video2.setMediaController(mediaControls)
        binding.video2.setVideoURI(
            Uri.parse(
                "android.resource://"
                        + packageName + "/" + R.raw.test_video
            )
        )
        binding.video2.requestFocus()
        binding.video2.start()
        binding.video2.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
            true
        }

        binding.video2.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occurred " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }
    }
}
