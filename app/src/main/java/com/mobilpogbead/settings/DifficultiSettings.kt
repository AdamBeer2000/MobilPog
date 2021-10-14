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
                "Pont szórzó:${diffisultiSetting.mult}\n" +
                        "Életek száma: 5\nLövedékek száma: 5\n" +
                        "Lövések közti idő: 1 másodperc\n" +
                        "Ellenséges lövedékek száma: 3\n" +
                        "Ellenségek lövések köztt idő:\n" +
                        "Egy másodperc")
            Difficulti.Medium->return DifficultiSetting(Difficulti.Medium,3,4,2000,4,500,
                "Pont szórzó:${diffisultiSetting.mult}\n" +
                        "Életek száma: 3\nLövedékek száma: 4\n" +
                        "Lövések közti idő: 2 másodperc\n" +
                        "Ellenséges lövedékek száma: 4\n" +
                        "Ellenségek lövések közti idő:\n" +
                        "Fél másodperc")
            Difficulti.Hard->return DifficultiSetting(Difficulti.Hard,1,3,3000,5,250,
                "Pont szórzó:${diffisultiSetting.mult}\n" +
                        "Életek száma: 1\n" +
                        "Lövedékek száma: 3\n" +
                        "Lövések közti idő: 3 másodperc\n" +
                        "Lövések közti idő: 3 másodperc\n" +
                        "Ellenséges lövedékek száma: 5\n" +
                        "Ellenségek lövések közti idő:\n" +
                        "Negyed másodperc")
        }
    }
}
