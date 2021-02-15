package com.appersiano.drawover

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

internal class SketchCanvas(mContext : Context, attributes: AttributeSet?) : View(mContext, attributes) {

    private val paint = Paint()
    private val path = Path()


    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 5f
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Get the coordinates of the touch event.
        val eventX = event?.x
        val eventY = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (eventX != null && eventY != null) {
                    path.moveTo(eventX, eventY)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (eventX != null && eventY != null) {
                    path.lineTo(eventX, eventY)
                }
            }
        }

        invalidate()
        return true
    }

    fun clearCanvas() {
        path.reset()
        invalidate()
    }

    fun setColor(randomColor: Int) {
        paint.color = randomColor
        invalidate()
    }
}

