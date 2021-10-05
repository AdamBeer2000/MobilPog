package com.mobilpogbead.entity

class Barricade(x: Int, y: Int):Entity(x, y)
{
    override var speed: Int=0
    override var hp: Int=Int.MAX_VALUE
    override var hitbox= arrayOf(arrayOf(false))

    override fun hit() {
        this.hp = 0
    }

    override fun collision(x:Int,y:Int):Boolean
    {
        if(super.collision(x, y))
        {
            hitbox[y][x]=false
            return true
        }
        return false
    }


}