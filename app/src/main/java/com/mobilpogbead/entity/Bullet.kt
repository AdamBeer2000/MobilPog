package com.mobilpogbead.entity

import android.graphics.Bitmap

class Bullet(x: Int, y: Int, gfx: Bitmap, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }
}