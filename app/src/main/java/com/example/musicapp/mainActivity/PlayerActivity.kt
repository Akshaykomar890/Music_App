package com.example.musicapp.mainActivity

import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.R
import com.example.musicapp.databinding.ActivityPlayerBinding
import com.example.musicapp.models.CategoryData
import com.example.musicapp.models.EachCategoryData
import java.lang.Exception

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    var mediaPlayer:MediaPlayer? = null
    var isPlaying:Boolean = false
    companion object{
       lateinit var category:EachCategoryData

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backButton.setOnClickListener{
            val intent = Intent(this@PlayerActivity,EachCategory::class.java)
            startActivity(intent)
            finish()
        }

        binding.songName.text = category.name
        binding.songArtist.text = category.subtitle
        Glide.with(binding.songPoster).load(category.imageUrl).apply(
            RequestOptions().transform(RoundedCorners(32))
        ).into(binding.songPoster)



        binding.playPause.setOnClickListener {
            if (isPlaying)
                pauses()
            else
                plays()
        }

    }

    fun plays(){
        val animator = ValueAnimator.ofFloat(0f,0.5f)
        animator.addUpdateListener { animator->
            binding.playPause.progress = animator.animatedValue as Float
        }
        animator.duration = 500
        animator.start()
        music()
        isPlaying =true
        binding.playPause.setOnClickListener {
            mediaPlayer?.pause()
            isPlaying =false
            pauses()
        }
    }

    fun pauses(){
        val animator = ValueAnimator.ofFloat(0.5f,1f)
        animator.addUpdateListener { animator->
            binding.playPause.progress = animator.animatedValue as Float
        }
        animator.duration = 500
        animator.start()
        binding.playPause.setOnClickListener {
            plays()
        }
    }
    fun music(){
        try {
            mediaPlayer = MediaPlayer()
            val url = category.url
            mediaPlayer!!.setDataSource(url)
            mediaPlayer!!.prepareAsync()
            mediaPlayer!!.setOnPreparedListener { mediaPlayer!!.start()
            }
        }
        catch (e:Exception){
            return
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.release()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer?.stop() // Stop playback
    }




}