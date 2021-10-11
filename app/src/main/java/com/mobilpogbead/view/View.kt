package com.mobilpogbead.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.get
import androidx.core.graphics.set
import com.mobilpogbead.entity.bullet.Bullet

import com.mobilpogbead.model.Model
import org.w3c.dom.Text
import kotlin.system.measureTimeMillis

class View(private val model:Model,private var pointCounter:TextView,private var lifeCounter:TextView,private var deathAnim: Bitmap)
{
    private  var height:Int=0
    private  var width:Int=0

    lateinit var imgv:ImageView

    lateinit var background:Bitmap

    fun bind(imgv: ImageView)
    {
        this.imgv=imgv
        height=imgv.maxHeight
        width=imgv.maxWidth
        imgv.setImageBitmap(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888))
    }

    fun update()
    {
        val renderedImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(renderedImage)

        for (obj in model.objects)
        {
            if(!obj.isDead())
                canvas.drawBitmap(obj.getCurrGfx(), obj.x.toFloat(), obj.y.toFloat(), null)
            else
                canvas.drawBitmap(deathAnim, obj.x.toFloat(), obj.y.toFloat(), null)
        }
        imgv.setImageBitmap(renderedImage)
        pointCounter.text="Points:${model.pointCounter}"
        lifeCounter.text="${model.player.getHp()}x"
        //Log.d("Update","Time:$elapsed s")
    }
}