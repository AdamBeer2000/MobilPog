package com.mobilpogbead.entity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.core.graphics.set
import java.lang.Math.abs
import java.util.*

class Barricade(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>):Entity(x, y,gfx,hitbox)
{
    override var speed: Int=0
    override var hp: Int=Int.MAX_VALUE

    override fun hit()
    {
        val x=abs(Random().nextInt()%gfx[0].width)
        val y=abs(Random().nextInt()%gfx[0].height)
        Log.d("BarrierHit","BarrierHit")
        for(i in x until x+15)
        {
            for(k in y until y+15)
            {
                super.gfx[0][i,k]=Color.BLACK
            }
        }
    }
}