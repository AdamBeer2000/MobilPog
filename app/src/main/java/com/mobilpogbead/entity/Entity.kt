package com.mobilpogbead.entity
import android.graphics.Bitmap
import java.nio.file.Paths
import java.util.ArrayList

abstract class Entity (var x:Int, var y:Int, var gfx: ArrayList<Bitmap>, var hitbox:Array<BooleanArray>)
{
    // ABSTRACT VARIABLES
    protected abstract var speed: Int
    protected abstract var hp: Int

    var width=gfx[0].width
    var height=gfx[0].height

    private var gfxShifter=0
    // ABSTRACT FUNCTIONS

    protected abstract fun hit()

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
            this.hit()
            other.hit()
            return true
        }
        return false
    }

    open fun collision(x:Int,y:Int):Boolean
    {
        if(hitbox[y][x])
        {
            hit()
            return true
        }
        return false
    }

    fun moveRight()
    {
        shiftGfx()
        x+=speed
    }
    fun moveLeft()
    {
        shiftGfx()
        x-=speed
    }
    fun moveUp()
    {
        shiftGfx()
        y-=speed
    }
    fun moveDown()
    {
        shiftGfx()
        y+=speed
    }
}