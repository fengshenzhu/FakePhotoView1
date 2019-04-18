package com.example.atemktx.photoview

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * Created by fengshzh on 04/18/2019.
 */
class FakeDrawable: Drawable() {

    val paint = Paint().apply {
        color = Color.GREEN
        textSize = 200f
    }

    override fun getIntrinsicWidth(): Int {
        return 10000
    }

    override fun getIntrinsicHeight(): Int {
        return 500
    }

    override fun draw(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.drawText("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890",
                0f,400f, paint)
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }
}