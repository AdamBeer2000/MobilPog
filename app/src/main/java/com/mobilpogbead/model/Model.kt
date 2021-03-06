package com.mobilpogbead.model
import android.content.Context
import android.util.Log
import com.mobilpogbead.audio.SingletonAudioManager
//import com.mobilpogbead.audio.AudioManager
import com.mobilpogbead.entity.*
import com.mobilpogbead.entity.enemies.*
import com.mobilpogbead.entity.SingletonEntityFactory
import com.mobilpogbead.entity.bullet.Bullet
import com.mobilpogbead.entity.bullet.EnemyBullet
import com.mobilpogbead.entity.bullet.PlayerBullet
import com.mobilpogbead.settings.DifficultiSettings
import java.lang.Math.abs
import java.lang.Math.min
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class Model(val boundaries:Boundaries, val context: Context)
{
    private val entityFactory=SingletonEntityFactory.getInstance()

    val objects=ArrayList<Entity>()

    val enemys=ArrayList<Enemy>()
    val bullets=ArrayList<Bullet>()
    val playerBullets=ArrayList<Bullet>()
    val enemyBullets=ArrayList<Bullet>()
    val barricades=ArrayList<Barricade>()

    var player:Player=entityFactory.createEntity<Player>(boundaries.xMax/2,boundaries.yMax-250) as Player
    var spaceship:Spaceship?=null
    var pointCounter=0

    var right:Boolean=true
    var left:Boolean=false
    var timeStart:Long=System.currentTimeMillis()
    var playerShootLastTime:Long=0
    var enemyShootLastTime:Long=0
    var difficulti= DifficultiSettings.getSetting()

    var result = 0

    fun getCurrTimeMillis()=System.currentTimeMillis()-timeStart


    private fun initState()
    {
        var ref=entityFactory.createEntity<Bug>(0,0)
        var shifty=50
        for(i in 0 until 5)
        {
            var shiftx=1
            shifty+=ref.getCurrGfx().height+15
            for(k in 0 until 11)
            {
                var newEnemy:Enemy?=null

                if(i==0) {newEnemy=entityFactory.createEntity<Squid>(shiftx,shifty) as Squid}
                else if(i>0&&i<3) {newEnemy=entityFactory.createEntity<Bug>(shiftx,shifty) as Bug}
                else{newEnemy=entityFactory.createEntity<Chonker>(shiftx,shifty) as Chonker}

                objects.add(newEnemy)
                enemys.add(newEnemy as Enemy)
                shiftx=newEnemy.x+newEnemy.getCurrGfx().width+15
            }
        }
        val barref=entityFactory.createEntity<Barricade>(0,0)as Barricade

        var shiftx=boundaries.xMax/8
        for(i in 0 until 4)
        {
            val bar=entityFactory.createEntity<Barricade>(shiftx,boundaries.yMax-400)as Barricade
            barricades.add(bar)
            objects.add(bar)
            shiftx+=2*barref.width
        }
        objects.add(player)
        var right=true
        var left=false
    }

    init
    {
        initState()
    }

    fun enemyShoot()
    {
        if(enemyBullets.count()< min(difficulti.enemyBulletNum,enemys.size)&&!player.isDead()&&System.currentTimeMillis()-enemyShootLastTime>=difficulti.enemyShootIntervalMilli)
        {
            SingletonAudioManager.playShoot(context)

            val enemy=enemys[abs(Random().nextInt())%enemys.size]
            val bullet: EnemyBullet =entityFactory.createEntity<EnemyBullet>(enemy.x,enemy.y) as EnemyBullet
            objects.add(bullet)
            enemyBullets.add(bullet)
            bullets.add(bullet)
            enemyShootLastTime=System.currentTimeMillis()
        }
    }
    fun shoot()
    {
        if(playerBullets.count()<difficulti.playerBulletNum&&!player.isDead()&&System.currentTimeMillis()-playerShootLastTime>=difficulti.playerShootIntervalMilli)
        {
            SingletonAudioManager.playShoot(context)

            val bullet: PlayerBullet =entityFactory.createEntity<PlayerBullet>(player.x+25,player.y-25) as PlayerBullet
            objects.add(bullet)
            playerBullets.add(bullet)
            bullets.add(bullet)
            playerShootLastTime=System.currentTimeMillis()
        }
    }

    fun winCheckh():Boolean
    {
        return if(enemys.size==0)
        {
            result = 1
            true
        }
        else
        {
            false
        }
    }

    fun failCheckh():Boolean
    {
        if (player.isDead())
        {
            result = 2
            return true
        }
        for(enemy in enemys)
        {
            if(enemy.y+enemy.height+400>=boundaries.yMax)
            {
                return true
            }
        }
        return false
    }

    fun progress()
    {
        if(abs(Random().nextInt()%100)>=99&&spaceship==null)
        {
            spaceship=entityFactory.createEntity<Spaceship>(boundaries.xMax,50)as Spaceship
            if(spaceship!=null)
            {
                objects.add(spaceship as Spaceship)
            }
        }

        for(Bullet in playerBullets)
        {
            Bullet.moveUp()
        }
        for(Bullet in enemyBullets)
        {
            Bullet.moveDown()
        }

        spaceship?.moveLeft()

        enemyShoot()

        if(right)
        {
            for(obj in enemys)
            {
                obj.moveRight()
                if(obj.x+obj.width>=boundaries.xMax)
                {
                    left=true
                    right=false
                    Log.d("Movement","Collide")
                }
            }
            if(!right)
            {
                for(obj in enemys)
                {
                    obj.moveDown()
                }
            }
            return
        }
        if(left)
        {
            for(obj in enemys)
            {
                obj.moveLeft()
                if(obj.x<=boundaries.xMin)
                {
                    left=false
                    right=true
                    Log.d("Movement","Collide")
                }
            }
            if(!left)
            {
                for(obj in enemys)
                {
                    obj.moveDown()
                }
            }
            return
        }

    }
    fun checkHits()
    {
        for(bullet in playerBullets)
        {
            if(spaceship?.let { bullet.collision(it) } == true)
            {
                SingletonAudioManager.playExplosion(context)
                Log.d("Hit","Hit")
            }
            for(enenmy in enemys)
            {
                if(bullet.collision(enenmy))
                {
                    SingletonAudioManager.playExplosion(context)
                    Log.d("Hit","Hit")
                }
            }
            for(enenmybullet in enemyBullets)
            {
                if(bullet.collision(enenmybullet))
                {
                    SingletonAudioManager.playExplosion(context)
                    Log.d("Hit","Hit")
                }
            }
        }

        for(bullet in enemyBullets)
        {
            if(bullet.collision(player))
            {
                SingletonAudioManager.playExplosion(context)
                Log.d("Hit","Hit")
            }
        }

        for(barr in barricades)
        {
            for(bullet in bullets)
            {
                if(barr.collision(bullet))
                {
                    SingletonAudioManager.playExplosion(context)
                    Log.d("Hit","Hit")
                }
            }
        }
        clearDead()
    }
    fun clearDead()
    {
        for(obj in objects)
        {
            if(obj.isDead())
            {
                if(obj is Enemy)
                {
                    pointCounter+= (obj as Enemy).getPoint()
                }

                Log.d("Point system","Points : $pointCounter")
                safeRemove(obj)
            }
        }
        if(player.isDead())
        {
            objects.remove(player)
        }
        if(spaceship?.isDead() == true)
        {
            pointCounter+= spaceship?.getPoint()!!
            spaceship=null
        }
    }
    fun safeRemove(obj:Entity?)
    {
        objects.remove(obj)
        enemys.remove(obj)
        playerBullets.remove(obj)
        enemyBullets.remove(obj)
        barricades.remove(obj)
        if(obj is Spaceship)
        {
            spaceship?.hit(player)
            spaceship=null
        }
    }

    fun cleanObjects()
    {
        clearDead()
        for(obj in objects)
        {
            if(obj is Bullet||obj is Spaceship)
            if(obj.x>boundaries.xMax+15||obj.x<boundaries.xMin-15
                ||obj.y>boundaries.yMax+15||obj.y<boundaries.yMin-15)
            {
                safeRemove(obj)
            }
        }
    }
    fun cleanAll()
    {
        for(obj in objects)
        {
            safeRemove(obj)
        }
        objects.clear()
    }
}