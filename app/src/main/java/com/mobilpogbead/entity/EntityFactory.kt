package com.mobilpogbead.entity

import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.os.IResultReceiver
import android.util.Log
import androidx.core.graphics.get
import androidx.core.graphics.set
import java.lang.Exception
import java.util.ArrayList

class EntityFactory(val resources:HashMap<String,ArrayList<Bitmap>>)
{
    fun bitmapToHitbox(bit:Bitmap):Array<BooleanArray>
    {
        var hitbox: Array<BooleanArray>
        hitbox=Array(bit.width){BooleanArray(bit.height)}
        for(x in 0 until bit.width)
        {
            for(y in 0 until bit.height )
            {
                hitbox[x][y] = (bit[x,y]!=Color.BLACK)
            }
        }
        return hitbox
    }

    inline fun<reified T : Entity>createEntity(x:Int,y:Int):Entity
    {
        var str:String
        when (T::class)
        {
            Barricade::class->str="Barricade"
            Bullet::class->str="Bullet"
            Enemy::class->str="Enemy"
            Player::class->str="Player"
            else->throw Exception("NO resource found")
        }
        val bitmap=resources[str]

        if(bitmap!=null)
        {
            when (T::class)
            {
                Barricade::class->return Barricade(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Bullet::class->return Bullet(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Enemy::class->return Enemy(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                Player::class->return Player(x,y,bitmap,bitmapToHitbox(bitmap[0]))
                else->throw Exception("NO resource found")
            }
        }

        Log.d("EntityFactory","Entity not found")
        throw Exception("NO resource found")
    }
}