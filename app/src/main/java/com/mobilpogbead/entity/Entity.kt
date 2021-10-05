package com.mobilpogbead.entity
import java.nio.file.Paths

abstract class Entity (var x:Int,var y:Int)
{

    // ABSTRACT VARIABLES
    protected abstract var speed: Int
    protected abstract var hp: Int
    abstract var hitbox:Array<Array<Boolean>>

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