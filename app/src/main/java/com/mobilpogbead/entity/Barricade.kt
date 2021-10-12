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
import com.mobilpogbead.entity.bullet.PlayerBullet
import java.lang.Math.pow
import kotlin.collections.ArrayList
import kotlin.math.floor
import kotlin.math.sqrt


class Barricade(x: Int, y: Int, gfx: ArrayList<Bitmap>, hitbox:Array<BooleanArray>):Entity(x, y,gfx,hitbox)
{
    override var speed: Int=0
    override var hp: Int=Int.MAX_VALUE

    var r=pow(20.0,2.0)

    private fun blastCords(other:Bullet):ArrayList<Point>
    {
        val array=ArrayList<Point>()
        var blastEpicenter=Point()
        if(other is PlayerBullet)
        {
            blastEpicenter.x=other.x+other.width/2
            blastEpicenter.y=other.y+other.height
        }
        else
        {
            blastEpicenter.x=other.x+other.width/2
            blastEpicenter.y=other.y
        }

        Log.d("BlastEpicenter","$blastEpicenter")
        for(i in this.x until this.x+this.getCurrGfx().width)
        {
            for(k in this.y until this.y+this.getCurrGfx().height)
            {
                val u=(pow((i-blastEpicenter.x).toDouble(),2.0)+pow((k-blastEpicenter.y).toDouble(),2.0))
                if(u<r)
                {
                    array.add(Point(i-this.x,k-this.y))
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
        val newGfx: Bitmap = gfx[0].copy(Bitmap.Config.ARGB_8888, true)
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