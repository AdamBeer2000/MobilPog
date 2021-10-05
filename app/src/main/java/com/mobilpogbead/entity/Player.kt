package com.mobilpogbead.entity

class Player(x: Int,y: Int): Entity(x, y)
{
    override var speed: Int = 0
    override var hp: Int = 3
    override var hitbox = arrayOf(arrayOf(false))

    override fun hit() {
        this.hp = 0
    }
}