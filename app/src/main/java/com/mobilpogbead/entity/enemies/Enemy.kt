package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import android.util.Log
import com.mobilpogbead.entity.Entity
import java.util.ArrayList
import kotlin.math.min


open class Enemy(x: Int, y: Int, gfx:ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 5
    override var hp: Int = 1
    open var point=10

    companion object
    {
        @JvmStatic
        var enemyNum:Int=-1

        @JvmStatic
        fun getCurrEnemyNum():Int
        {
            return enemyNum
        }

        @JvmStatic
        fun kill()
        {
            enemyNum--
        }
    }

    fun killit()
    {
        kill()
    }

    init
    {
        if(enemyNum!=-1)
        {
            enemyNum+=1
        }
        else
        {
            enemyNum=1
        }
    }

    private fun adjustedSpeed():Int
    {
        return speed+ ((55-enemyNum)/5)
    }

    override fun moveRight()
    {
        if(!isDead())
        {
            super.shiftGfx()
            x+=adjustedSpeed()
        }
        Log.d("Speed","Speed:${adjustedSpeed()} Num:$enemyNum")
    }
    override fun moveLeft()
    {
        if(!isDead())
        {
            shiftGfx()
            x -= adjustedSpeed()
        }
    }
    override fun moveUp()
    {
        if(!isDead())
        {
            shiftGfx()
            y -= adjustedSpeed()
        }
    }
    override fun moveDown()
    {
        if(!isDead())
        {
            shiftGfx()
            y += adjustedSpeed()
        }
    }
}