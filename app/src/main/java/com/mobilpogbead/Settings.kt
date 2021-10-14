package com.mobilpogbead

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import com.mobilpogbead.audio.SingletonAudioManager
import com.mobilpogbead.settings.AudioSettings
import com.mobilpogbead.settings.DifficultiSettings

class Settings : AppCompatActivity()
{

    lateinit var radioButtonEasy:RadioButton
    lateinit var radioButtonMedium:RadioButton
    lateinit var radioButtonHard:RadioButton
    lateinit var difficultyRadioButtonGroup:RadioGroup
    lateinit var difficultyInfoText:TextView

    lateinit var musicRadioGrupOn:RadioButton
    lateinit var musicRadioGrupOff:RadioButton
    lateinit var musicRadioGrup:RadioGroup

    lateinit var volumeSeekBar:SeekBar
    lateinit var percentageView:TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        radioButtonEasy=findViewById(R.id.difficultyRadioGroupEasy)
        radioButtonMedium=findViewById(R.id.difficultyRadioGroupMedium)
        radioButtonHard=findViewById(R.id.difficultyRadioGroupHard)
        difficultyRadioButtonGroup=findViewById(R.id.difficultyRadioGroup)

        difficultyInfoText=findViewById(R.id.difficoltyInfoText)

        musicRadioGrupOn=findViewById(R.id.musicRadioGrupOn)
        musicRadioGrupOff=findViewById(R.id.musicRadioGrupOff)
        musicRadioGrup=findViewById(R.id.musicRadioGrup)

        volumeSeekBar=findViewById(R.id.volumeSeekBar)
        volumeSeekBar.setMax(10)
        volumeSeekBar.progress= (AudioSettings.audioVolume*10).toInt()


        percentageView=findViewById(R.id.percentageView)

        percentageView.text="${(AudioSettings.audioVolume*100).toInt()}%"

        when(DifficultiSettings.diffisultiSetting)
        {
            DifficultiSettings.Difficulti.Easy->{
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupEasy)
            }
            DifficultiSettings.Difficulti.Medium->
            {
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupMedium)
            }
            DifficultiSettings.Difficulti.Hard->
            {
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupHard)
            }
        }
        difficultyInfoText.text=DifficultiSettings.getSetting().info

        difficultyRadioButtonGroup.setOnCheckedChangeListener { radioGroup, i ->
            changeDificulti(i)
        }

        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean)
            {
                AudioSettings.audioVolume=seekBar.progress/10F
                percentageView.text="${seekBar.progress*10}%"
                Log.d("Seekbar","${seekBar.progress}")
            }
            override fun onStartTrackingTouch(seekBar: SeekBar)
            {
                AudioSettings.audioVolume=seekBar.progress/10F
                percentageView.text="${seekBar.progress*10}%"
                Log.d("Seekbar","${seekBar.progress}")
            }
            override fun onStopTrackingTouch(seekBar: SeekBar)
            {
                AudioSettings.audioVolume=seekBar.progress/10F
                percentageView.text="${seekBar.progress*10}%"
                Log.d("Seekbar","${seekBar.progress}")
            }
        })

        when(AudioSettings.MusicStateSetting)
        {
            AudioSettings.MusicState.On->musicRadioGrup.check(R.id.musicRadioGrupOn)
            AudioSettings.MusicState.Off->musicRadioGrup.check(R.id.musicRadioGrupOff)
        }

        musicRadioGrup.setOnCheckedChangeListener { radioGroup, i ->
            if(i==R.id.musicRadioGrupOn)
            {
                AudioSettings.MusicStateSetting=AudioSettings.MusicState.On
                SingletonAudioManager.playMenuMusic(this)
            }
            else
            {
                AudioSettings.MusicStateSetting=AudioSettings.MusicState.Off
                SingletonAudioManager.stopMenuMusic()
            }
        }
    }

    fun changeDificulti(id:Int)
    {
        if(id==R.id.difficultyRadioGroupEasy) DifficultiSettings.diffisultiSetting=DifficultiSettings.Difficulti.Easy
        if(id==R.id.difficultyRadioGroupMedium) DifficultiSettings.diffisultiSetting=DifficultiSettings.Difficulti.Medium
        if(id==R.id.difficultyRadioGroupHard) DifficultiSettings.diffisultiSetting=DifficultiSettings.Difficulti.Hard
        difficultyInfoText.text=DifficultiSettings.getSetting().info
        Log.d("difficulty","Set to ${DifficultiSettings.diffisultiSetting}")
    }

    fun musicOn()
    {
        AudioSettings.MusicStateSetting=AudioSettings.MusicState.On
    }

    fun musicOff()
    {
        AudioSettings.MusicStateSetting=AudioSettings.MusicState.Off
    }
}