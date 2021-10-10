package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import com.mobilpogbead.entity.Entity
import java.util.ArrayList

class Chonker (x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Enemy(x, y,gfx,hitbox)
{
    override var point=10
}