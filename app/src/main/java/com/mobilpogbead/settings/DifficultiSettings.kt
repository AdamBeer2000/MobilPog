package com.mobilpogbead.settings

object DifficultiSettings
{
    enum class Difficulti(val mult:Int)
    {
        Easy(1),Medium(3),Hard(5)
    }
    data class DifficultiSetting(val difficulti:Difficulti,val playerHealth:Int,
                                 val playerBulletNum:Int,val playerShootIntervalMilli:Long,
                                 val enemyBulletNum:Int,val enemyShootIntervalMilli:Long)
    {

    }

    var diffisultiSetting=Difficulti.Medium

    fun getSetting():DifficultiSetting
    {
        when(diffisultiSetting)
        {
            Difficulti.Easy->return DifficultiSetting(Difficulti.Easy,5,5,1000,3,1000)
            Difficulti.Medium->return DifficultiSetting(Difficulti.Medium,3,4,2000,4,500)
            Difficulti.Hard->return DifficultiSetting(Difficulti.Hard,1,3,3000,5,250)
        }
    }
}
