package com.mobilpogbead.entity

abstract class Entity (private var x:Int,private var y:Int)
{
    // ABSTRACT VARIABLES
    abstract var speed: Int
    abstract var hp: Int
    abstract var hitbox:Array<Array<Byte>>

    // ABSTRACT FUNCTIONS
    abstract fun hit()
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