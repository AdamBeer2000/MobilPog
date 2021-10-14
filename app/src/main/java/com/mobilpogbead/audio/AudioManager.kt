package com.mobilpogbead.audio

import android.content.Context
import android.media.MediaPlayer
import com.mobilpogbead.R

class AudioManager(private val context: Context)
{
    var mediaPlayer: MediaPlayer? = null
    var secondaryPlayer: MediaPlayer? = null

    fun playMenuMusic()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.menu_music)
            mediaPlayer!!.isLooping = true;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer!!.isLooping = true;
            mediaPlayer!!.start()
        }
    }

    fun playExplosion()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.explosion)
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
        if (mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.shoot)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            if(mediaPlayer!!.isPlaying)
            {
                if (secondaryPlayer == null)
                {
                    secondaryPlayer = MediaPlayer.create(context, R.raw.shoot)
                    secondaryPlayer!!.isLooping = false;
                    secondaryPlayer!!.start()
                }
                else
                {
                    secondaryPlayer!!.isLooping = false;
                    secondaryPlayer!!.start()
                }
            }
            else
            {
                mediaPlayer!!.isLooping = false;
                mediaPlayer!!.start()
            }
        }

    }

    fun playWin()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.win)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.win)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }

    fun playLose()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.lose)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.lose)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }
}