package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import java.util.ArrayList

class Squid (x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Enemy(x, y,gfx,hitbox)
{
    override var point=50
}