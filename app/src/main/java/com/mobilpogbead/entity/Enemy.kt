package com.mobilpogbead.entity

import com.mobilpogbead.R
import android.graphics.BitmapFactory
import android.content.res.Resources


class Enemy(x: Int,y: Int): Entity(x, y)
{
    private fun loadRes():Array<Array<Boolean>>
    {
        val hitboxor= arrayOf(
            arrayOf(false)
        )
        return hitboxor
    }

    override var speed: Int = 0
    override var hp: Int = Int.MAX_VALUE
    override var hitbox = loadRes()

    override fun hit() {
        this.hp = 0
    }
}