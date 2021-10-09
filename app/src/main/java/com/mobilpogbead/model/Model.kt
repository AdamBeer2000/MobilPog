package com.mobilpogbead.model
import android.util.Log
import com.mobilpogbead.entity.*

class Model(private val entityFactory:EntityFactory,val boundaries:Boundaries)
{
    val objects=ArrayList<Entity?>()

    val enemys=ArrayList<Enemy>()
    val bullets=ArrayList<Bullet>()
    val playerBullets=ArrayList<Bullet>()
    val enemyBullets=ArrayList<Bullet>()

    var player:Player=entityFactory.createEntity<Player>(300,boundaries.yMax-250) as Player

    init
    {
        var ref=entityFactory.createEntity<Enemy>(0,0)

        var shifty=1
        for(i in 0 until 5)
        {
            var shiftx=1
            shifty+=ref.getCurrGfx().height+15
            for(k in 0 until 8)
            {
                var newEnemy=entityFactory.createEntity<Enemy>(shiftx,shifty)
                objects.add(newEnemy)
                enemys.add(newEnemy as Enemy)
                shiftx=newEnemy.x+newEnemy.getCurrGfx().width+15
            }
        }

        objects.add(player)
    }
    var right:Boolean=true
    var left:Boolean=false
    fun shoot()
    {
        if(playerBullets.count()<=3)
        {
            val bullet:Bullet=entityFactory.createEntity<Bullet>(player.x+25,player.y-20) as Bullet
            objects.add(bullet)

            playerBullets.add(bullet)
            bullets.add(bullet)
        }
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
        if(right)
        {
            for(obj in enemys)
            {
                obj.moveRight()
                if(obj.x>=boundaries.xMax)
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
        for(bullet in bullets)
        {
            for(enenmy in enemys)
            {
                if(bullet.collision(enenmy)) Log.d("Hit","Hit")
            }
        }
        clearDead()
    }
    fun clearDead()
    {
        for(obj in objects)
        {
            if(obj==null)
            {
                saveRemove(obj)
            }
            else if(obj.isDead())
            {
                saveRemove(obj)
            }
        }
    }
    fun saveRemove(obj:Entity?)
    {
        objects.remove(obj)
        enemys.remove(obj)
        playerBullets.remove(obj)
        enemyBullets.remove(obj)
    }
    fun cleanOutOfBounsObjects()
    {
        for(obj in objects)
        {
            if(obj==null)
            {
                objects.remove(obj)
            }
            else if(obj.x>boundaries.xMax+10||obj.x<boundaries.xMin-10
                ||obj.y>boundaries.yMax+10||obj.y<boundaries.yMin-10)
            {
                saveRemove(obj)
            }
        }
    }
}