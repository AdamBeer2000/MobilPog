package com.mobilpogbead.entity

import com.mobilpogbead.R
import android.graphics.BitmapFactory
import android.content.res.Resources
import android.graphics.Bitmap
import java.util.ArrayList


class Enemy(x: Int, y: Int, gfx:ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 10
    override var hp: Int = 1

    override fun hit() {
        this.hp=this.hp-1
    }
}