package com.mobilpogbead.settings

import com.google.gson.annotations.SerializedName

data class MySettings(@SerializedName("musicStateSetting") var musicStateSetting:AudioSettings.MusicState,
                      @SerializedName("audioVolume") var audioVolume:Float,
                      @SerializedName("difficultySetting") var difficultySetting:DifficultiSettings.Difficulti
)
{
    fun useThis()
    {
        AudioSettings.MusicStateSetting=musicStateSetting
        AudioSettings.audioVolume=audioVolume
        DifficultiSettings.diffisultiSetting=difficultySetting
    }
}
inline fun getCurrentSettings():MySettings
{
    return MySettings(AudioSettings.MusicStateSetting,
        AudioSettings.audioVolume,
        DifficultiSettings.diffisultiSetting
    )
}