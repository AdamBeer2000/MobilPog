package com.mobilpogbead.entity
import android.graphics.Bitmap
import java.nio.file.Paths

abstract class Entity (var x:Int, var y:Int, var gfx: Bitmap, var hitbox:Array<BooleanArray>)
{
    // ABSTRACT VARIABLES
    protected abstract var speed: Int
    protected abstract var hp: Int

    // ABSTRACT FUNCTIONS

    protected abstract fun hit()

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
        x+=speed
    }
    fun moveLeft()
    {
        x-=speed
    }
    fun moveUp()
    {
        y-=speed
    }
    fun moveDown()
    {
        y+=speed
    }
}