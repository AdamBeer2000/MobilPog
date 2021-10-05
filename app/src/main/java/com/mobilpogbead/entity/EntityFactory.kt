package com.mobilpogbead.entity

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.os.IResultReceiver
import android.util.Log
import androidx.core.graphics.get
import androidx.core.graphics.set

class EntityFactory(val resources:HashMap<String,Bitmap>)
{
    fun bitmapToHitbox(bitmapId:String):Array<BooleanArray>
    {
        var hitbox: Array<BooleanArray>
        val bit=resources[bitmapId]
        if(bit!=null)
        {
            hitbox=Array(bit.width){BooleanArray(bit.height)}
            for(x in 0 until bit.width)
            {
                for(y in 0 until bit.height )
                {
                    hitbox[x][y] = (bit[x,y]==Color.WHITE)
                }
            }
            return hitbox
        }
        else
        {
            Log.d("EntityFactory","bitmapId not found")
            return Array<BooleanArray>(0){BooleanArray(0)}
        }

    }

    inline fun<reified T : Entity>createEntity(x:Int,y:Int):Entity?
    {
        when (T::class)
        {
            Barricade::class->return Barricade(x,y,bitmapToHitbox("Barricade"))
            Bullet::class->return Bullet(x,y,bitmapToHitbox("Bullet"))
            Enemy::class->return Enemy(x,y,bitmapToHitbox("Enemy"))
            Player::class->return Player(x,y,bitmapToHitbox("Player"))
        }
        Log.d("EntityFactory","Entity not found")
        return null
    }
}