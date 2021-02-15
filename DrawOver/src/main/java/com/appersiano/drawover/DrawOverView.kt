package com.appersiano.drawover

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import java.util.*

/**
 * DrawOver is a Compound View that display a big drawable view via user input with a small toolbar
 * with some additional functionalities
 */
class DrawOverView(mContext: Context, attributes: AttributeSet?) : FrameLayout(mContext, attributes) {

    private val ll_root: LinearLayout
    private val shot: ImageButton
    private val move: ImageView
    private val clear: ImageButton
    private val color: ImageButton
    private val close: ImageView
    private val sketchCanvas: SketchCanvas

    init {
        val view = inflate(mContext, R.layout.layout_drawover, this)
        ll_root = view.findViewById(R.id.ll_tools)
        shot = view.findViewById(R.id.imb_shot)
        move = view.findViewById(R.id.iv_move)
        clear = view.findViewById(R.id.imb_clear)
        color = view.findViewById(R.id.imb_color)
        close = view.findViewById(R.id.iv_close)
        sketchCanvas = view.findViewById(R.id.sketchCanvas)
        view.elevation = 10f
        setLineColor(Color.RED)
        setup()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setup() {
        close.setOnClickListener {
            this@DrawOverView.visibility = View.GONE
        }

        shot.setOnClickListener {
            ll_root.isVisible = false
            val screenBitmap = getScreenShotOfView(rootView)
            ll_root.isVisible = true

            val path: String = MediaStore.Images.Media.insertImage(
                context.contentResolver,
                screenBitmap,
                "title",
                null
            )
            val screenshotUri = Uri.parse(path)

            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Intent.EXTRA_STREAM, screenshotUri)
                type = "image/png"
            }
            context.startActivity(Intent.createChooser(emailIntent, "Send email using"))
        }

        clear.setOnClickListener {
            sketchCanvas.clearCanvas()
        }

        color.setOnClickListener {
            val rnd = Random()
            val randomColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            setLineColor(randomColor)
        }

        var dX = 0f
        var dY = 0f
        move.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_MOVE -> {
                    ll_root.x = motionEvent.rawX + dX
                    ll_root.y = motionEvent.rawY + dY
                }
                MotionEvent.ACTION_UP -> {
                    ll_root.alpha = 1f
                }
                MotionEvent.ACTION_DOWN -> {
                    dX = ll_root.x - motionEvent.rawX
                    dY = ll_root.y - motionEvent.rawY
                    ll_root.alpha = 0.5f
                }
            }
            true
        }
    }

    /**
     * Set the color of the line
     */
    private fun setLineColor(@ColorInt randomColor: Int) {
        color.imageTintList = ColorStateList.valueOf(randomColor)
        sketchCanvas.setColor(randomColor)
    }

    /**
     * Take screenshot and take bitmap
     */
    private fun getScreenShotOfView(view: View): Bitmap? {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas)
        else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

}