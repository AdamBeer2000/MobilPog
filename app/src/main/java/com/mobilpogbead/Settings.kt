package com.mobilpogbead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.mobilpogbead.settings.AudioSettings
import com.mobilpogbead.settings.DifficultiSettings

class Settings : AppCompatActivity()
{

    lateinit var radioButtonEasy:RadioButton
    lateinit var radioButtonMedium:RadioButton
    lateinit var radioButtonHard:RadioButton
    lateinit var difficultyRadioButtonGroup:RadioGroup
    lateinit var difficultyInfoText:TextView

    //todo scroll lissener hangerősséghez

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        radioButtonEasy=findViewById(R.id.difficultyRadioGroupEasy)
        radioButtonMedium=findViewById(R.id.difficultyRadioGroupMedium)
        radioButtonHard=findViewById(R.id.difficultyRadioGroupHard)
        difficultyRadioButtonGroup=findViewById(R.id.difficultyRadioGroup)

        difficultyInfoText=findViewById(R.id.difficoltyInfoText)

        

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