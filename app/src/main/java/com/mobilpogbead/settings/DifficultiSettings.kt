package com.mobilpogbead.settings

object DifficultiSettings
{
    enum class Difficulti(val mult:Int)
    {
        Easy(1),Medium(3),Hard(5)
    }
    data class DifficultiSetting(val difficulti:Difficulti,val playerHealth:Int,
                                 val playerBulletNum:Int,val playerShootIntervalMilli:Long,
                                 val enemyBulletNum:Int,val enemyShootIntervalMilli:Long,val info:String)
    {

    }

    var diffisultiSetting=Difficulti.Medium

    fun getSetting():DifficultiSetting
    {
        when(diffisultiSetting)
        {
            Difficulti.Easy->return DifficultiSetting(Difficulti.Easy,5,5,1000,3,1000,
                "Score multiplayer: ${diffisultiSetting.mult}\n" +
                        "Lives: 5\n" +
                        "Bullet number: 5\n" +
                        "Time between two shots: 1 sec\n" +
                        "Enemy's bullet number: 3\n" +
                        "Enemy's Time between two shots: 1 sec\n")
            Difficulti.Medium->return DifficultiSetting(Difficulti.Medium,3,4,2000,4,500,
                "Score multiplayer: ${diffisultiSetting.mult}\n" +
                        "Lives: 3\n" +
                        "Bullet number: 4\n" +
                        "Time between two shots: 2 sec\n" +
                        "Enemy's bullet number: 4\n" +
                        "Enemy's Time between two shots: half sec\n")
            Difficulti.Hard->return DifficultiSetting(Difficulti.Hard,1,3,3000,5,250,
                "Score multiplayer: ${diffisultiSetting.mult}\n" +
                        "Lives: 1\n" +
                        "Bullet number: 3\n" +
                        "Time between two shots: 3 sec\n" +
                        "Enemy's bullet number: 5\n" +
                        "Enemy's Time between two shots: 1/4 sec\n")
        }
    }
}
