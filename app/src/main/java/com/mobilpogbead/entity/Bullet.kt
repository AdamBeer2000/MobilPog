package com.mobilpogbead.entity

class Bullet(private var x: Int, private var y: Int): Entity(x, y)
{
    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE
    override var hitbox = arrayOf(arrayOf(false))

    override fun hit() {
        this.hp = 0
    }
}