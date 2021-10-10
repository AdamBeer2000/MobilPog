package com.mobilpogbead.entity

import android.graphics.Bitmap
import java.util.ArrayList

class Player(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 20
    override var hp: Int = 3

    override fun hit()
    {
        this.hp = 0
    }
}