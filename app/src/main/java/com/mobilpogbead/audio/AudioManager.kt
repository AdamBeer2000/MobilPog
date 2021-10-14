package com.mobilpogbead.audio

import android.content.Context
import android.media.MediaPlayer
import com.mobilpogbead.R

class AudioManager(private val context: Context)
{
    var menuMediaPlayer: MediaPlayer? = null
    var mediaPlayer: MediaPlayer? = null
    var secondaryPlayer: MediaPlayer? = null
    var soundVolume: Float = 1.0F
    var menuMusicVolume: Float = 1.0F


    fun playMenuMusic()
    {
        if(menuMediaPlayer == null)
        {
            menuMediaPlayer = MediaPlayer.create(context, R.raw.menu_music)
            menuMediaPlayer!!.setVolume(menuMusicVolume, menuMusicVolume)
            menuMediaPlayer!!.isLooping = true;
            menuMediaPlayer!!.start()
        }
        else
        {
            menuMediaPlayer!!.setVolume(menuMusicVolume, menuMusicVolume)
            menuMediaPlayer!!.isLooping = true;
            menuMediaPlayer!!.start()
        }
    }

    fun playExplosion()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.explosion)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.explosion)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }

    fun playShoot()
    {
        if (mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.shoot)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
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
                    secondaryPlayer!!.setVolume(soundVolume, soundVolume)
                    secondaryPlayer!!.isLooping = false;
                    secondaryPlayer!!.start()
                }
                else
                {
                    secondaryPlayer = MediaPlayer.create(context, R.raw.shoot)
                    secondaryPlayer!!.setVolume(soundVolume, soundVolume)
                    secondaryPlayer!!.isLooping = false;
                    secondaryPlayer!!.start()
                }
            }
            else
            {
                mediaPlayer = MediaPlayer.create(context, R.raw.shoot)
                mediaPlayer!!.setVolume(soundVolume, soundVolume)
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
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.win)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()

        }
    }

    fun playLose()
    {
        if(mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.lose)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
        else
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.lose)
            mediaPlayer!!.setVolume(soundVolume, soundVolume)
            mediaPlayer!!.isLooping = false;
            mediaPlayer!!.start()
        }
    }


    fun setMusicVolume(value: Float)
    {
        menuMusicVolume = value
    }

    fun setSoundsVolume(value: Float)
    {
        soundVolume = value
    }
}