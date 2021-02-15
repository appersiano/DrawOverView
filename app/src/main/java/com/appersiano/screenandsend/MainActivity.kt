package com.appersiano.screenandsend

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.appersiano.drawover.DrawOver
import java.util.*


class MainActivity : AppCompatActivity() {
    var showDraw = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button)?.setOnClickListener {
            findViewById<View>(R.id.drawOver).visibility = View.VISIBLE
        }
    }
}
