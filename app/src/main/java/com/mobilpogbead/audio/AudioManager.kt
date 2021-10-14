package com.mobilpogbead.audio

import android.content.Context
import android.media.MediaPlayer
import com.mobilpogbead.R
import com.mobilpogbead.settings.AudioSettings

class AudioManager(private val context: Context)
{
    var mediaPlayer: MediaPlayer? = null

    fun playMenuMusic()
    {
        if(AudioSettings.MusicStateSetting==AudioSettings.MusicState.On)
        {
            if(mediaPlayer == null)
            {
                mediaPlayer = MediaPlayer.create(context, R.raw.menu_music)
                mediaPlayer!!.isLooping = false;
                mediaPlayer!!.start()
            }
            else
            {
                mediaPlayer!!.isLooping = false;
                mediaPlayer!!.start()
            }
        }
    }

    fun playBoom()
    {
        if(mediaPlayer == null)
        {
            //mediaPlayer = MediaPlayer.create(context, R.raw.boom)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }

    fun playShoot()
    {
        if(mediaPlayer == null)
        {
            //mediaPlayer = MediaPlayer.create(context, R.raw.shoot)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }

    fun playWin()
    {
        if(mediaPlayer == null)
        {
            //mediaPlayer = MediaPlayer.create(context, R.raw.win)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }

    fun playLose()
    {
        if(mediaPlayer == null)
        {
            //mediaPlayer = MediaPlayer.create(context, R.raw.lose)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }
}