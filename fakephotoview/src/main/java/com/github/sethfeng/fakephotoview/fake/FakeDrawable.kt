package com.example.atemktx.photoview

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log

/**
 * Created by fengshzh on 04/18/2019.
 */
class FakeDrawable : Drawable() {

    companion object {
        private const val TAG = "FakeDrawable"
    }

    val NUM = 100
    val NUM2 = 500

    val paint = Paint().apply {
        color = Color.GREEN
        textSize = 200f
    }

    override fun getIntrinsicWidth(): Int {
        return 1440 * NUM
    }

    override fun getIntrinsicHeight(): Int {
        return 500
    }

    override fun draw(canvas: Canvas) {
        val timeStart = System.currentTimeMillis()
        canvas.drawColor(Color.GRAY)
        for (i in 0 until NUM2) {
            canvas.drawText(
                i.toString(),
                1440f * i, 400f, paint
            )
        }
//        Thread.sleep(17)
        Log.d(TAG, "draw fps ${1000 / (System.currentTimeMillis() - timeStart)}")
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }
}