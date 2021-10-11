package com.mobilpogbead.controller

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import android.widget.TextView
import com.mobilpogbead.entity.SingletonEntityFactory
import com.mobilpogbead.model.Boundaries
import com.mobilpogbead.model.Model
import com.mobilpogbead.view.View
import kotlin.system.exitProcess

class Controller(private var context: Context,var boundaries:Boundaries, pointcounter:TextView,lifecounter:TextView)
{
    private lateinit var sensorManager: SensorManager;

    val model=Model(boundaries)
    val view=View(model,pointcounter,lifecounter)

    var gyro_x: Double = 0.0
    var gyro_y: Double = 0.0
    var gyro_z: Double = 0.0

    fun setUpSensor()
    {
        sensorManager = this.context.getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED
        sensorManager.registerListener(gyroSensorListener,
            gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);

        Log.d("SETUP-SENSOR","STARTED")

        if(gyroSensor == null) {
            Log.e("SENSOR", "Gyro sensor not available.");
            exitProcess(420); // Close app
        }

        Log.d("SETUP-SENSOR","SUCCESSFUL")
    }

    var gyroSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(p0: SensorEvent?) {
            if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED
            {
                val rightLeft = p0.values[0]
                val upDown = p0.values[1]
                val frontBehind = p0.values[2]

                Log.d("SENSOR ","x: $rightLeft")
                Log.d("SENSOR ","y: $upDown")
                Log.d("SENSOR ","z: $frontBehind")

                gyro_x = p0.values[0].toDouble()
                gyro_y = p0.values[1].toDouble()
                gyro_z = p0.values[2].toDouble()
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            Log.d("SENSOR: ", "ACCURACY CHANGED")
        }
    }
    fun getGyroArray(): Array<Double>
    {
        val result = arrayOf(gyro_x, gyro_y, gyro_z)
        return result
    }
    fun move()
    {
        val y: Double = getGyroArray()[1];
        if(boundaries.yMin<=model.player.x-1&&model.player.x+model.player.width+1<=boundaries.yMax)
        {
            if(y < 0.0){
                model.player.moveLeft()
            }
            else if(y > 0.0){
                model.player.moveRight()
            }
        }
    }

}