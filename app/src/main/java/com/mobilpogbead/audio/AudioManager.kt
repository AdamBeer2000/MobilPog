package com.mobilpogbead.audio

import android.content.Context
import android.media.MediaPlayer
import com.mobilpogbead.R

class AudioManager(private val context: Context)
{
    var mediaPlayer: MediaPlayer? = null

    fun playBeliever()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.menu_music)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.start()
        }
    }

    fun playBoom(){}
    fun playShoot(){}
    fun playWin(){}
    fun playGameOver(){}
}