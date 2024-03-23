package com.example.musicapp.mainActivity

import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.databinding.ActivityPlayerBinding
import com.example.musicapp.models.CategoryData
import com.example.musicapp.models.EachCategoryData
import java.lang.Exception

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    lateinit var player:ExoPlayer
    companion object{
       lateinit var category:EachCategoryData
       lateinit var getData:CategoryData

    }
   @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


       /* binding.backButton.setOnClickListener {
            val intent = Intent(this@PlayerActivity, EachCategory::class.java)
            startActivity(intent)
            finish()
        }*/



       binding.heartButton.setOnClickListener {
           Toast.makeText(this@PlayerActivity,"Saved to favorites",Toast.LENGTH_SHORT).show()

       }

        binding.songName.text = category.name
        binding.songArtist.text = category.subtitle
        Glide.with(binding.songPoster).load(category.imageUrl).apply(
            RequestOptions().transform(RoundedCorners(32))
        ).into(binding.songPoster)


        player = ExoPlayer.Builder(this).build()
        binding.musicPlayer.player = player
        val mediaItem = MediaItem.fromUri(category.url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

   }
    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}