package com.mobilpogbead.entity

class Bullet(x: Int,y: Int,hitbox:Array<BooleanArray>): Entity(x, y,hitbox)
{
    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }
}