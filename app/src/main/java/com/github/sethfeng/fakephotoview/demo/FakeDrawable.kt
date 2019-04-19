package com.example.atemktx.photoview

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log
import com.github.sethfeng.fakephotoview.demo.ScreenUtil
import kotlin.math.ceil
import kotlin.math.max

/**
 * Created by fengshzh on 04/18/2019.
 */
class FakeDrawable(var width: Int = 100, var height: Int = 100) : Drawable() {

    companion object {
        private const val TAG = "FakeDrawable"
    }

    private val mTextSize = 200f

    private val paint = Paint().apply {
        color = Color.GREEN
        textSize = mTextSize
        textAlign = Paint.Align.LEFT
        style = Paint.Style.STROKE
    }

    private val drawablePaint = Paint().apply {
        color = Color.parseColor("#3f00ff00")
    }

    override fun getIntrinsicWidth(): Int {
        return width
    }

    override fun getIntrinsicHeight(): Int {
        return height
    }

    override fun draw(canvas: Canvas) {
        val timeStart = System.currentTimeMillis()
        // FIXME: 普通View支持透明度，TextureView绘制透明度背景会导致多帧叠加
        canvas.drawColor(Color.parseColor("#3f0000ff"))
//        canvas.drawColor(Color.parseColor("#0000ff"))

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), drawablePaint)
        for (i in 0 until ceil(width * 1f / ScreenUtil.SCREEN_WIDTH).toInt()) {
            canvas.drawText(i.toString(), ScreenUtil.SCREEN_WIDTH * i.toFloat(), mTextSize, paint)
            canvas.drawRect(ScreenUtil.SCREEN_WIDTH * i.toFloat(), 0f, ScreenUtil.SCREEN_WIDTH * i + 100f, mTextSize, paint)
        }
//        Thread.sleep(17)
        Log.d(TAG, "draw fps ${1000 / max(1, System.currentTimeMillis() - timeStart)}")
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }
}