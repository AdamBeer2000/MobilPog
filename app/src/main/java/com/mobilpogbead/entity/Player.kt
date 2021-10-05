package com.mobilpogbead.entity

class Player(x: Int,y: Int,hitbox:Array<BooleanArray>): Entity(x, y,hitbox)
{
    override var speed: Int = 0
    override var hp: Int = 3

    override fun hit() {
        this.hp = 0
    }
}