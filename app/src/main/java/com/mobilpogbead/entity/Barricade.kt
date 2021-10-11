package com.mobilpogbead.entity

import android.R.attr
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Log
import androidx.core.graphics.get
import androidx.core.graphics.set
import java.lang.Exception
import java.lang.Math.abs
import java.util.*
import android.R.attr.bitmap
import android.graphics.Point
import com.mobilpogbead.entity.bullet.Bullet
import java.lang.Math.pow
import kotlin.collections.ArrayList
import kotlin.math.floor
import kotlin.math.sqrt


class Barricade(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>):Entity(x, y,gfx,hitbox)
{
    override var speed: Int=0
    override var hp: Int=Int.MAX_VALUE

    private fun blastCords(other:Bullet):ArrayList<Point>
    {
        val array=ArrayList<Point>()
        for(i in this.x until this.x+this.getCurrGfx().width)
        {
            for(k in this.y until this.y+this.getCurrGfx().height)
            {
                if(other.x<=i&&i<=other.x+other.width)
                {
                    if(other.y<=k&&k<=other.y+other.height)
                    {
                        array.add(Point(i-this.x,k-this.y))
                    }
                }
            }
        }
        return array
    }

    private fun hitboxColl(points:ArrayList<Point>):Boolean
    {
        for(p in points)
        {
            if(hitbox[p.x][p.y])
            return true
        }
        return false
    }

    override fun hit(other:Entity)
    {
        val x=abs(Random().nextInt()%gfx[0].width)
        val y=abs(Random().nextInt()%gfx[0].height)

        val newGfx: Bitmap = gfx[0].copy(Bitmap.Config.ARGB_8888, true)
        val r=Random()
        val bls=blastCords(other as Bullet)

        for(p in bls)
        {
            newGfx[p.x,p.y]=Color.BLACK
            hitbox[p.x][p.y]=false
        }

        super.gfx[0]=newGfx
    }

    override fun collision(other: Entity): Boolean
    {
        if(other is Bullet)
        {
            if(this.isDead()||other.isDead())
            {
                return false
            }
            if (this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.height + this.y > other.y)
            {
                if(hitboxColl(blastCords(other)))
                {
                    this.hit(other)
                    other.hit(this)
                }
                return true
            }
            return false
        }
        else
        {
            return super.collision(other)
        }

    }
}