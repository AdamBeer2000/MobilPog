package com.mobilpogbead.controller

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.graphics.Bitmap
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

class Controller(private var context: Context,var boundaries:Boundaries,
                 pointcounter:TextView,
                 lifecounter:TextView,
                 deathanim: Bitmap
)
{
    private lateinit var sensorManager: SensorManager;

    val model=Model(boundaries)
    val view=View(model,pointcounter,lifecounter,deathanim)

    //var gyro_x: Double = 0.0
    //var gyro_y: Double = 0.0
    //var gyro_z: Double = 0.0

    private var mValuesMagnet = FloatArray(3)
    private var mValuesAccel = FloatArray(3)
    private var mValuesOrientation = FloatArray(3)

    private var mRotationMatrix = FloatArray(9)

    fun setUpSensor()
    {
        sensorManager = this.context.getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED

        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED

        //sensorManager.registerListener(gyroSensorListener,
          //  gyroSensor, SensorManager.SENSOR_DELAY_FASTEST);

        sensorManager.registerListener(gyroSensorListener,
            magneticSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(gyroSensorListener,
            accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);


        Log.d("SETUP-SENSOR","STARTED")

        if(gyroSensor == null) {
            Log.e("SENSOR", "Gyro sensor not available.");
            //exitProcess(420); // Close app
        }

        Log.d("SETUP-SENSOR","SUCCESSFUL")
    }

    var gyroSensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(p0: SensorEvent?) {
            /*if(p0?.sensor?.type == Sensor.TYPE_GYROSCOPE)//TYPE_GYROSCOPE_UNCALIBRATED
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
            }*/

            when (p0?.sensor?.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    //System.arraycopy(p0.values, 0, mValuesAccel, 0, 3)
                    mValuesAccel[0] = p0.values[0]
                    mValuesAccel[1] = p0.values[1]
                    mValuesAccel[2] = p0.values[2]
                }
                Sensor.TYPE_MAGNETIC_FIELD -> {
                    //System.arraycopy(p0.values, 0, mValuesMagnet, 0, 3)
                    mValuesMagnet[0] = p0.values[0]
                    mValuesMagnet[1] = p0.values[1]
                    mValuesMagnet[2] = p0.values[2]
                }
            }
            SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
            SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            Log.d("SENSOR: ", "ACCURACY CHANGED")
        }
    }

    fun logger()
    {

        for (i in 0..8)
        Log.d("ROTATION: ",  "${i}. value: ${mRotationMatrix[i]}")
    }

    fun move()
    {
        val y: Float = mRotationMatrix[6];
        //
        if(boundaries.xMin<=model.player.x-model.player.width-1&&y > 0.0)
        {
            model.player.moveLeft()
        }else if (model.player.x+model.player.width+1<=boundaries.xMax && y < 0.0){
            model.player.moveRight()
        }

    }
}