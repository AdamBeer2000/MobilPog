package com.mobilpogbead

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import com.mobilpogbead.audio.SingletonAudioManager
import com.mobilpogbead.settings.AudioSettings
import java.io.File
import java.util.*
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.mobilpogbead.settings.MySettings
import com.mobilpogbead.settings.getCurrentSettings
import java.io.StringReader
import java.lang.reflect.Type

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