package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import com.mobilpogbead.entity.Entity
import java.util.ArrayList


open class Enemy(x: Int, y: Int, gfx:ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 15
    override var hp: Int = 1
    open var point=10

    override fun hit()
    {
        this.hp=this.hp-1
    }
}