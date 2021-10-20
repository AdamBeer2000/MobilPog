package com.mobilpogbead

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.google.gson.Gson
import com.mobilpogbead.audio.SingletonAudioManager
import com.mobilpogbead.settings.AudioSettings
import com.mobilpogbead.settings.DifficultiSettings
import java.io.File
import com.mobilpogbead.settings.getCurrentSettings


class AudioSettingsView : AppCompatActivity() {

    lateinit var musicRadioGrupOn: RadioButton
    lateinit var musicRadioGrupOff: RadioButton
    lateinit var musicRadioGrup: RadioGroup

    lateinit var volumeSeekBar: SeekBar
    lateinit var percentageView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_settings_audio)

        musicRadioGrupOn = findViewById(R.id.musicRadioGrupOn)
        musicRadioGrupOff = findViewById(R.id.musicRadioGrupOff)
        musicRadioGrup = findViewById(R.id.musicRadioGrup)

        volumeSeekBar = findViewById(R.id.volumeSeekBar)
        volumeSeekBar.setMax(10)
        volumeSeekBar.progress = (AudioSettings.audioVolume * 10).toInt()


        percentageView = findViewById(R.id.percentageView)

        percentageView.text = "${(AudioSettings.audioVolume * 100).toInt()}%"

        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                AudioSettings.audioVolume = seekBar.progress / 10F
                percentageView.text = "${seekBar.progress * 10}%"
                Log.d("Seekbar", "${seekBar.progress}")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                AudioSettings.audioVolume = seekBar.progress / 10F
                percentageView.text = "${seekBar.progress * 10}%"
                Log.d("Seekbar", "${seekBar.progress}")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                AudioSettings.audioVolume = seekBar.progress / 10F
                percentageView.text = "${seekBar.progress * 10}%"
                Log.d("Seekbar", "${seekBar.progress}")
            }
        })

        when (AudioSettings.MusicStateSetting) {
            AudioSettings.MusicState.On -> musicRadioGrup.check(R.id.musicRadioGrupOn)
            AudioSettings.MusicState.Off -> musicRadioGrup.check(R.id.musicRadioGrupOff)
        }

        musicRadioGrup.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.musicRadioGrupOn) {
                AudioSettings.MusicStateSetting = AudioSettings.MusicState.On
                SingletonAudioManager.playMenuMusic(this)
            } else {
                AudioSettings.MusicStateSetting = AudioSettings.MusicState.Off
                SingletonAudioManager.stopMenuMusic()
            }
        }
    }

    override fun onPause()
    {
        super.onPause()
        val gson= Gson()
        val dir = File(this.filesDir ,"Configs")

        if(!dir.exists())
        {
            dir.mkdir()
        }

        val writer=File("${dir.absolutePath}/config.json")
        writer.writeText(gson.toJson(getCurrentSettings()))
        val test=writer.readLines()
        Log.d("SaveSettings","Saved text: ${test}")
    }
}

