package com.mobilpogbead.entity

import android.graphics.Bitmap
import java.util.ArrayList

class Barricade(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>):Entity(x, y,gfx,hitbox)
{
    override var speed: Int=0
    override var hp: Int=Int.MAX_VALUE

    override fun hit() {
        this.hp = 0
    }

    override fun collision(x:Int,y:Int):Boolean
    {
        if(super.collision(x, y))
        {
            super.hitbox[y][x]=false
            return true
        }
        return false
    }


}