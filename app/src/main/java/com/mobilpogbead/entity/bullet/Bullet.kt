package com.mobilpogbead.entity.bullet

import android.graphics.Bitmap
import com.mobilpogbead.entity.Entity
import java.util.ArrayList

open class Bullet(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 25
    override var hp: Int = 1
}