package com.mobilpogbead.entity
import android.graphics.Bitmap
import java.nio.file.Paths
import java.util.ArrayList

abstract class Entity (var x:Int, var y:Int, var gfx: ArrayList<Bitmap>, var hitbox:Array<BooleanArray>)
{
    // ABSTRACT VARIABLES
    protected abstract var speed: Int
    protected abstract var hp: Int

    @JvmName("getHp1")
    fun getHp()=hp

    var width=gfx[0].width
    var height=gfx[0].height

    private var gfxShifter=0
    // ABSTRACT FUNCTIONS

    open fun hit(other: Entity)
    {
        this.hp=this.hp-1
    }

    fun isDead()=hp<=0

    fun getCurrGfx():Bitmap
    {
        return gfx[gfxShifter]
    }

    private fun shiftGfx()
    {
        if(gfx.size!=1)
        {
            gfxShifter++
            if(gfx.size==gfxShifter)
            {
                gfxShifter=0
            }
        }
    }

    open fun collision(other:Entity):Boolean
    {
        if(this.isDead()||other.isDead())
        {
            return false
        }
        if (this.x < other.x + other.width &&
            this.x + this.width > other.x &&
            this.y < other.y + other.height &&
            this.height + this.y > other.y)
        {
            this.hit(other)
            other.hit(this)
            return true
        }
        return false
    }

    fun moveRight()
    {
        if(!isDead())
        {
            shiftGfx()
            x+=speed
        }
    }
    fun moveLeft()
    {
        if(!isDead())
        {
            shiftGfx()
            x -= speed
        }
    }
    fun moveUp()
    {
        if(!isDead())
        {
            shiftGfx()
            y -= speed
        }
    }
    fun moveDown()
    {
        if(!isDead())
        {
            shiftGfx()
            y += speed
        }
    }
}