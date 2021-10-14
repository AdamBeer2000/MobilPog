package com.mobilpogbead.settings

import com.mobilpogbead.audio.SingletonAudioManager

object AudioSettings
{
    enum class MusicState
    {
        On,Off
    }
    var MusicStateSetting=MusicState.On
    var audioVolume:Float=0.5F
    set(value)
    {
        if(value in 0.0..1.0)
        {
            SingletonAudioManager.setVolume(value)
            field=value
        }
    }
}
