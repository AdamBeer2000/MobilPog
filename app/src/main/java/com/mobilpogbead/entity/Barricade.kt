package com.mobilpogbead.entity

class Barricade(private var x: Int, private var y: Int):Entity(x, y)
{
    override var speed: Int
        get() = this.speed
        set(value) { this.speed = value }

    override var hp: Int
        get() = this.hp
        set(value) { this.hp = value }

    override var hitbox: Array<Array<Byte>>
        get() = this.hitbox
        set(value) { this.hitbox = value }


    override fun hit() {
        this.hp = 0
    }


}