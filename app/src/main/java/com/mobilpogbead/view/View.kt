package com.mobilpogbead.view

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.get
import androidx.core.graphics.set
import com.mobilpogbead.entity.Enemy
import com.mobilpogbead.entity.Entity
import com.mobilpogbead.model.Model

class View(private val model:Model)
{
    var renderedImage = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        private set
    lateinit var imgv:ImageView
    init
    {

    }

    fun bind(imgv: ImageView)
    {
        this.imgv=imgv
        imgv.setImageBitmap(renderedImage)
    }

    fun update()
    {

        Log.d("update","Update starts")
        Log.d("update","Obj num: "+model.objects.size)
        for(x in 0 until 500)
        {
            for(y in 0 until 500)
            {
                renderedImage[x, y] = Color.BLACK
            }
        }
        for (obj in model.objects)
        {
            if(obj!=null)
            {
                val gfx=obj.gfx
                Log.d("update","Obj hitbox size: "+obj.hitbox.size*obj.hitbox[0].size)
                for(x in 0 until gfx.width)
                {
                    for(y in 0 until gfx.height)
                    {
                            Log.d("update","true")
                            renderedImage[x+obj.x, y+obj.y]=gfx[x, y]
                    }
                }
            }
        }
        imgv.setImageBitmap(renderedImage)
    }
}