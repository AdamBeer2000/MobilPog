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


class DifficultySettingsView : AppCompatActivity() {

    lateinit var radioButtonEasy: RadioButton
    lateinit var radioButtonMedium: RadioButton
    lateinit var radioButtonHard: RadioButton
    lateinit var difficultyRadioButtonGroup: RadioGroup
    lateinit var difficultyInfoText: TextView

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
        setContentView(R.layout.activity_settings_difficulty)

        radioButtonEasy = findViewById(R.id.difficultyRadioGroupEasy)
        radioButtonMedium = findViewById(R.id.difficultyRadioGroupMedium)
        radioButtonHard = findViewById(R.id.difficultyRadioGroupHard)
        difficultyRadioButtonGroup = findViewById(R.id.difficultyRadioGroup)

        difficultyInfoText = findViewById(R.id.difficoltyInfoText)

        when (DifficultiSettings.diffisultiSetting) {
            DifficultiSettings.Difficulti.Easy -> {
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupEasy)
            }
            DifficultiSettings.Difficulti.Medium -> {
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupMedium)
            }
            DifficultiSettings.Difficulti.Hard -> {
                difficultyRadioButtonGroup.check(R.id.difficultyRadioGroupHard)
            }
        }

        difficultyInfoText.text = DifficultiSettings.getSetting().info

        difficultyRadioButtonGroup.setOnCheckedChangeListener { radioGroup, i ->
            changeDificulti(i)
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

    fun changeDificulti(id: Int)
    {
        if (id == R.id.difficultyRadioGroupEasy) DifficultiSettings.diffisultiSetting =
            DifficultiSettings.Difficulti.Easy
        if (id == R.id.difficultyRadioGroupMedium) DifficultiSettings.diffisultiSetting =
            DifficultiSettings.Difficulti.Medium
        if (id == R.id.difficultyRadioGroupHard) DifficultiSettings.diffisultiSetting =
            DifficultiSettings.Difficulti.Hard
        difficultyInfoText.text = DifficultiSettings.getSetting().info
        Log.d("difficulty", "Set to ${DifficultiSettings.diffisultiSetting}")
    }
}

