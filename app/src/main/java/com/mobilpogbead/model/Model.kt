package com.mobilpogbead.model
import android.util.Log
import com.mobilpogbead.entity.*
import com.mobilpogbead.entity.enemies.*
import com.mobilpogbead.entity.SingletonEntityFactory
import com.mobilpogbead.entity.bullet.Bullet
import com.mobilpogbead.entity.bullet.EnemyBullet
import com.mobilpogbead.entity.bullet.PlayerBullet
import java.lang.Math.abs
import java.util.*
import kotlin.collections.ArrayList

class Model(val boundaries:Boundaries)
{
    private val entityFactory=SingletonEntityFactory.getInstance()

    val objects=ArrayList<Entity>()

    val enemys=ArrayList<Enemy>()
    val bullets=ArrayList<Bullet>()
    val playerBullets=ArrayList<Bullet>()
    val enemyBullets=ArrayList<Bullet>()

    var player:Player=entityFactory.createEntity<Player>(300,boundaries.yMax-250) as Player

    var pointCounter=0

    init
    {
        var ref=entityFactory.createEntity<Bug>(0,0)

        var shifty=1
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
        objects.add(player)
    }
    var right:Boolean=true
    var left:Boolean=false

    fun enemyShoot()
    {
        if(enemyBullets.count()<=3&&!player.isDead())
        {
            val enemy=enemys[abs(Random().nextInt())%enemys.size]
            val bullet: EnemyBullet =entityFactory.createEntity<EnemyBullet>(enemy.x,enemy.y) as EnemyBullet
            objects.add(bullet)
            enemyBullets.add(bullet)
            bullets.add(bullet)
        }
    }

    fun shoot()
    {
        if(playerBullets.count()<=2&&!player.isDead())
        {
            val bullet: PlayerBullet =entityFactory.createEntity<PlayerBullet>(player.x+25,player.y-25) as PlayerBullet
            objects.add(bullet)
            playerBullets.add(bullet)
            bullets.add(bullet)
        }
    }

    fun winCheckh():Boolean
    {
        return enemys.size==0
    }
    fun failCheckh():Boolean
    {
        return player.isDead()
    }

    fun progress()
    {
        for(Bullet in playerBullets)
        {
            Bullet.moveUp()
        }
        for(Bullet in enemyBullets)
        {
            Bullet.moveDown()
        }
        shoot()
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
            for(enenmy in enemys)
            {
                if(bullet.collision(enenmy)) Log.d("Hit","Hit")
            }
        }
        for(bullet in enemyBullets)
        {
            if(bullet.collision(player)) Log.d("Hit","Hit")
        }
        clearDead()
    }
    fun clearDead()
    {
        for(obj in objects)
        {
            if(obj.isDead())
            {
                if(obj is Enemy)pointCounter+= (obj as Enemy).point
                Log.d("Point system","Points : $pointCounter")
                safeRemove(obj)
            }
        }
        if(player.isDead())
        {
            objects.remove(player)
        }
    }
    fun safeRemove(obj:Entity?)
    {
        objects.remove(obj)
        enemys.remove(obj)
        playerBullets.remove(obj)
        enemyBullets.remove(obj)
    }

    fun cleanOutOfBounsBullets()
    {
        for(obj in bullets)
        {
            if(obj.x>boundaries.xMax+5||obj.x<boundaries.xMin-5
                ||obj.y>boundaries.yMax+5||obj.y<boundaries.yMin-5)
            {
                safeRemove(obj)
            }
        }
    }
}