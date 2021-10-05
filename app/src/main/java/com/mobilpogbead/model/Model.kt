package com.mobilpogbead.model
import android.graphics.Bitmap
import android.util.Log
import com.mobilpogbead.entity.Enemy
import com.mobilpogbead.entity.Entity
import com.mobilpogbead.entity.EntityFactory

class Model(private val entityFactory:EntityFactory)
{
    val objects=ArrayList<Entity>()

    init
    {
        val newEnemy=entityFactory.createEntity<Enemy>(0,0)
        if(newEnemy!=null)
        {
            objects.add(newEnemy)
            Log.d("entityFactory","Enemy added")
        }
        else
        {
            Log.d("entityFactory","Enemy null")
        }
    }

    fun checkHits()
    {
        for(obj in objects)
        {
            if(false)//todo hitbox checkh
            {

            }
        }
    }
    fun clearDead()
    {
        for(obj in objects)
        {

        }
    }
}