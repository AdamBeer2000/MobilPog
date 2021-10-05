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

    init
    {
        for(x in 0 until 500)
        {
            for(y in 0 until 500)
            {
                renderedImage[x, y] = Color.BLACK
            }
        }
    }

    fun bind(imgv: ImageView)
    {
        imgv.setImageBitmap(renderedImage)
    }

    fun update()
    {
        Log.d("update","Update starts")
        Log.d("update","Obj num: "+model.objects.size)
        for (obj in model.objects)
        {
            val hitbox=obj.hitbox
            Log.d("update","Obj hitbox size: "+obj.hitbox.size*obj.hitbox[0].size)
            for(x in 0 until hitbox.size)
            {
                for(y in 0 until hitbox[x].size)
                {
                    if(hitbox[x][y])
                    {
                        Log.d("update","true")
                        renderedImage[x+obj.x, y+obj.y]=Color.WHITE
                    }
                }
            }
        }
    }
}