package com.example.atemktx.photoview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView.ScaleType
import kotlin.math.abs

/**
 * Created by fengshzh on 04/17/2019.
 */
open class FakeImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "FakeImageView"

        fun matrixChanged(matrix1: Matrix?, matrix2: Matrix?) : Boolean{
            if (matrix1 == null && matrix2 == null) return false
            if (matrix1 == null || matrix2 == null) return true
            val values1 = FloatArray(9)
            matrix1.getValues(values1)
            val values2 = FloatArray(9)
            matrix2.getValues(values2)
            for (i in 0 until 9) {
                if (abs(values1[i] - values2[i]) > 0.1) {
                    return true
                }
            }
            return false
        }
    }

    private var mDrawable: Drawable? = null
    private var mMatrix = Matrix()
    private var mScaleType: ScaleType = ScaleType.FIT_START

//    override
    fun getDrawable(): Drawable? {
        Log.d(TAG, "getDrawable $mDrawable")
        return mDrawable
//        return super.getDrawable()
    }
//    override
    open fun getScaleType(): ScaleType {
        Log.d(TAG, "getScaleType $mScaleType")
        return mScaleType
//        return super.getScaleType()
    }
//    override
    open fun setImageMatrix(matrix: Matrix) {
    if (!matrixChanged(mMatrix, matrix)) return
        Log.d(TAG, "setImageMatrix $matrix")
        mMatrix.set(matrix)
        configureBounds()
        invalidate()

//        super.setImageMatrix(matrix)
    }
//    override
    open fun getImageMatrix(): Matrix {
        Log.d(TAG, "getImageMatrix $matrix")
        return mMatrix
    }
//    override
    open fun setScaleType(scaleType: ScaleType) {
        Log.d(TAG, "setScaleType $scaleType")
        mScaleType = scaleType
        requestLayout()
        invalidate()

//        super.setScaleType(scaleType)
    }
//    override
    open fun setImageDrawable(drawable: Drawable) {
        Log.d(TAG, "setImageDrawable $drawable")
        mDrawable = drawable
        updateDrawable()
        requestLayout()
        invalidate()

//        super.setImageDrawable(drawable)
    }
//    override
    open fun setImageResource(resId: Int) {
        Log.d(TAG, "setImageResource")
    }
//    override
    open fun setImageURI(uri: Uri) {
        Log.d(TAG, "setImageURI")
    }

    private fun updateDrawable() {
        configureBounds()
    }

    private fun configureBounds() {
        val dwidth = mDrawable!!.intrinsicWidth
        val dheight = mDrawable!!.intrinsicHeight
        val vwidth = width
        val vheight = height
        mDrawable!!.setBounds(0, 0, dwidth, dheight)
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        if (mDrawable == null) {
            return  // couldn't resolve the URI
        }

        val saveCount = canvas!!.saveCount
        canvas.concat(mMatrix)
        mDrawable!!.draw(canvas)
        canvas.restoreToCount(saveCount)
    }

}
