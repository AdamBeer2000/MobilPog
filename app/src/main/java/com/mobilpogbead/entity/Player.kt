package com.mobilpogbead.entity

import android.graphics.Bitmap
import com.mobilpogbead.settings.DifficultiSettings
import java.util.ArrayList

class Player(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>): Entity(x, y,gfx,hitbox)
{
    override var speed: Int = 15
    override var hp: Int = DifficultiSettings.getSetting().playerHealth
}