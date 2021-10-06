package com.mobilpogbead.entity

import android.graphics.Bitmap

class Player(x: Int, y: Int, gfx: Bitmap, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 0
    override var hp: Int = 3

    override fun hit() {
        this.hp = 0
    }
}