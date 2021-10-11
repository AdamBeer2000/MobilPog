package com.mobilpogbead.entity.enemies

import android.graphics.Bitmap
import java.util.ArrayList

class Spaceship (x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Enemy(x, y,gfx,hitbox)
{
    override var speed=30
    override var point=300
}