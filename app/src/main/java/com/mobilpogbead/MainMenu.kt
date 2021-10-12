package com.mobilpogbead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainMenu : AppCompatActivity()
{
    fun toLeaderBoard(v:View)
    {
        startActivity(Intent(this,MainActivity::class.java))
    }
    fun toGame(v:View)
    {
        startActivity(Intent(this,LeaderboardActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }
}