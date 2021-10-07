package com.mobilpogbead.entity

import android.graphics.Bitmap
import java.util.ArrayList

class Bullet(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }
}