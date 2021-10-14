package com.mobilpogbead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.mobilpogbead.audio.SingletonAudioManager
import java.util.*
//import com.mobilpogbead.audio.AudioManager
class MainMenu : AppCompatActivity()
{
    val mTimer = Timer()
    lateinit var Title:ImageView
    fun toLeaderBoard(v:View)
    {
        startActivity(Intent(this,LeaderboardActivity::class.java))
    }
    fun toGame(v:View)
    {
        startActivity(Intent(this,MainActivity::class.java))
        SingletonAudioManager.stopMenuMusic()
    }
    fun toSettings(v:View)
    {
        startActivity(Intent(this,Settings::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        Title=findViewById(R.id.imageView2)
    }

    override fun onStart() {
        super.onStart()
        SingletonAudioManager.playMenuMusic(this)
    }

    override fun onResume() {
        super.onResume()
        SingletonAudioManager.playMenuMusic(this)
    }
}