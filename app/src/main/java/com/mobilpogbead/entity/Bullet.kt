package com.mobilpogbead.entity

class Bullet(x: Int,y: Int): Entity(x, y)
{
    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE
    override var hitbox = arrayOf(arrayOf(false))

    override fun hit() {
        this.hp = 0
    }
}