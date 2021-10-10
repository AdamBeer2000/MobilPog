package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import java.util.ArrayList

class Bug (x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Enemy(x, y,gfx,hitbox)
{
    override var point=30
}