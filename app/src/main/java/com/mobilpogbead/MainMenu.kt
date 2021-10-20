package com.mobilpogbead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.mobilpogbead.audio.SingletonAudioManager
import java.io.File
import java.util.*
import com.google.gson.*
import com.mobilpogbead.settings.MySettings

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
    fun toAudioSettings(v:View)
    {
        startActivity(Intent(this,AudioSettingsView::class.java))
    }
    fun toDifficultySettings(v:View)
    {
        startActivity(Intent(this,DifficultySettingsView::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main_menu)
        Title=findViewById(R.id.imageView2)

        val dir = File(this.filesDir ,"Configs")

        if(dir.exists())
        {
            val gson= Gson()

            val file=File("${dir.absolutePath}/config.json")
            val lines=file.readLines()
            val jsonString=lines.joinToString(" ")


            val setting:MySettings = Gson().fromJson(jsonString,MySettings::class.java)
            Log.d("LoadSettings","${setting}")
            setting.useThis()
        }
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