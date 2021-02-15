package com.appersiano.screenandsend

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent.*
import androidx.appcompat.app.AppCompatActivity
import com.appersiano.drawover.DrawOver
import java.util.*


class MainActivity : AppCompatActivity() {
    var showDraw = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        val sd = ShakeDetector {
//            if (showDraw){
//                showDraw = false
//                findViewById<View>(R.id.sketchCanvas).isVisible = false
//                findViewById<View>(R.id.ll_tools).isVisible = false
//            } else {
//                showDraw = true
//                findViewById<View>(R.id.sketchCanvas).isVisible = true
//                findViewById<View>(R.id.ll_tools).isVisible = true
//            }
//        }
//        sd.start(sensorManager)
    }
}
