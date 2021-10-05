package com.mobilpogbead.entity

import com.mobilpogbead.R
import android.graphics.BitmapFactory
import android.content.res.Resources


class Enemy(x: Int,y: Int,hitbox:Array<BooleanArray>): Entity(x, y,hitbox)
{

    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }
}