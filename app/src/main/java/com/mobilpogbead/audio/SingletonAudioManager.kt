package com.mobilpogbead.audio

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.mobilpogbead.R
import com.mobilpogbead.settings.AudioSettings

object SingletonAudioManager
{
    /*
    var menuMediaPlayer: MediaPlayer? = null
    var mediaPlayer: MediaPlayer? = null
    var secondaryPlayer: MediaPlayer? = null
    */

    private val audioEffects=ArrayList<MediaPlayer>()
    private var menuMusic:MediaPlayer?=null

    fun playMenuMusic(context:Context)
    {
        if(AudioSettings.MusicStateSetting==AudioSettings.MusicState.On)
        {
            if(menuMusic==null)
            {
                menuMusic = MediaPlayer.create(context, R.raw.menu_music)
                menuMusic?.isLooping = true;
                menuMusic?.start()
                Log.d("MediaPlayer","Created menu_music")
                menuMusic?.setOnCompletionListener { player ->
                    audioEffects.remove(player)
                    Log.d("MediaPlayer","Removed menu_music")
                }
            }else if(!menuMusic?.isPlaying!!)
            {
                menuMusic?.start()
            }
        }
    }

    fun stopMenuMusic()
    {
        menuMusic?.pause()
    }

    fun playExplosion(context:Context)
    {
        val mediaPlayer = MediaPlayer.create(context, R.raw.explosion)
        mediaPlayer.isLooping = false;
        mediaPlayer.start()
        Log.d("MediaPlayer","Created explosion")
        mediaPlayer.setOnCompletionListener { player ->
            audioEffects.remove(player)
            Log.d("MediaPlayer","Removed explosion")
        }
        audioEffects.add(mediaPlayer)
    }

    fun playShoot(context:Context)
    {
        val mediaPlayer = MediaPlayer.create(context, R.raw.shoot)
        mediaPlayer.isLooping = false;
        mediaPlayer.start()
        Log.d("MediaPlayer","Created shoot")
        mediaPlayer.setOnCompletionListener { player ->
            audioEffects.remove(player)
            Log.d("MediaPlayer","Removed shoot")
        }
        audioEffects.add(mediaPlayer)
    }

    fun playWin(context:Context)
    {
        val mediaPlayer = MediaPlayer.create(context, R.raw.win)
        mediaPlayer.isLooping = false;
        mediaPlayer.start()
        Log.d("MediaPlayer","Created win")
        mediaPlayer.setOnCompletionListener { player ->
            audioEffects.remove(player)
            Log.d("MediaPlayer","Removed win")
        }
        audioEffects.add(mediaPlayer)
    }

    fun playLose(context:Context)
    {
        val mediaPlayer = MediaPlayer.create(context, R.raw.lose)
        mediaPlayer.isLooping = false;
        mediaPlayer.start()
        Log.d("MediaPlayer","Created lose")
        mediaPlayer.setOnCompletionListener { player ->
            audioEffects.remove(player)
            Log.d("MediaPlayer","Removed lose")
        }
        audioEffects.add(mediaPlayer)
    }

    fun setVolume(value: Float)
    {
        for(mediaPlayer in audioEffects)
        {
            mediaPlayer.setVolume(value,value)
        }
        menuMusic?.setVolume(value,value)
    }
}