package com.nikhil.alertdialogprac

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.nikhil.alertdialogprac.databinding.ActivityVideoView3Binding
import com.nikhil.alertdialogprac.databinding.ActivityVideoViewBinding

class VideoView3 : AppCompatActivity() {
    private val binding: ActivityVideoView3Binding by lazy{
        ActivityVideoView3Binding.inflate(layoutInflater)
    }
    private var player: ExoPlayer?=null
    private lateinit var playerView: PlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_video_view3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        playerView = findViewById(R.id.player_view)

        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        val uri = Uri.parse(videoUrl)
        val mediaItem = MediaItem.fromUri(uri)
        player?.apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }
    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}