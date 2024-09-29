package com.example.videoview


import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val videos = mutableListOf(
            Uri.parse("android.resource://$packageName/${R.raw.video}"),
            Uri.parse("android.resource://$packageName/${R.raw.video1}"),
            Uri.parse("android.resource://$packageName/${R.raw.video2}"),
            Uri.parse("android.resource://$packageName/${R.raw.video3}"),
            Uri.parse("android.resource://$packageName/${R.raw.video5}")
        )
        var index = 0
        val mediaController = MediaController(this)
        mediaController.setAnchorView(mediaController)

        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(videos[index])
        binding.videoView.requestFocus()
        binding.videoView.start()

        binding.buttonStop.setOnClickListener {
            binding.videoView.pause()
        }

        binding.buttonNext.setOnClickListener {
            if (index >= 0 && index < videos.size - 1) index++
            else if (index == videos.size - 1){
                index = 0
            }
            binding.videoView.setVideoURI(videos[index])
            binding.videoView.requestFocus()
            binding.videoView.start()
        }

        binding.buttonPrevious.setOnClickListener {
            if (index > 0 && index <= videos.size - 1) index--
            else if (index == 0){
                index = videos.size - 1
            }
            binding.videoView.setVideoURI(videos[index])
            binding.videoView.requestFocus()
            binding.videoView.start()
        }
    }

}