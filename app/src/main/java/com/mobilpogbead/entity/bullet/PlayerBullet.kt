package com.mobilpogbead.entity.bullet

import android.graphics.Bitmap
import com.mobilpogbead.entity.Entity
import com.mobilpogbead.entity.Player
import com.mobilpogbead.entity.enemies.Enemy
import java.util.ArrayList

class PlayerBullet(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>):Bullet(x,y,gfx,hitbox)
{
    override fun collision(other: Entity):Boolean
    {
        if(other !is Player) return super.collision(other)
        else return false
    }
}