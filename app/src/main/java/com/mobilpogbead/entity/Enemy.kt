package com.mobilpogbead.entity

import com.mobilpogbead.R
import android.graphics.BitmapFactory
import android.content.res.Resources
import android.graphics.Bitmap


class Enemy(x: Int, y: Int, gfx: Bitmap, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{

    override var speed: Int = 10
    override var hp: Int = Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }
}