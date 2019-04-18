package com.example.atemktx.photoview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.ImageView.ScaleType
import com.github.sethfeng.fakephotoview.Compat
import com.github.sethfeng.fakephotoview.fake.Fakeable
import kotlin.math.abs

/**
 * Created by fengshzh on 04/17/2019.
 */
open class FakeableView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Fakeable {

    companion object {
        private const val TAG = "FakeableView"

        fun matrixChanged(matrix1: Matrix?, matrix2: Matrix?): Boolean {
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

    override fun getParentFake(): ViewParent {
        return parent
    }

    override fun getContextFake(): Context {
        return context
    }

    override fun postFake(runnable: Runnable) {
        post(runnable)
    }

    override fun setOnTouchListenerFake(listener: OnTouchListener) {
        setOnTouchListener(listener)
    }

    override fun addOnLayoutChangeListenerFake(listener: OnLayoutChangeListener) {
        addOnLayoutChangeListener(listener)
    }

    override fun isInEditModeFake(): Boolean {
        return isInEditMode
    }

    override fun getRightFake(): Int {
        return right
    }

    override fun getBottomFake(): Int {
        return bottom
    }

    override fun getWidthFake(): Int {
        return width
    }

    override fun getPaddingLeftFake(): Int {
        return left
    }

    override fun getPaddingRightFake(): Int {
        return paddingRight
    }

    override fun getHeightFake(): Int {
        return height
    }

    override fun getPaddingTopFake(): Int {
        return paddingTop
    }

    override fun getPaddingBottomFake(): Int {
        return paddingBottom
    }

    override fun postOnAnimationFake(runnable: Runnable) {
        Compat.postOnAnimation(this, runnable)
    }

    override fun setFakeDrawable(drawable: Drawable?) {
        Log.d(TAG, "setFakeDrawable $drawable")
        mDrawable = drawable
        updateDrawable()
        requestLayout()
        invalidate()
    }

    override fun getFakeDrawable(): Drawable? {
        Log.d(TAG, "getFakeDrawable $mDrawable")
        return mDrawable
    }

    override fun getFakeScaleType(): ScaleType {
        Log.d(TAG, "getFakeScaleType $mScaleType")
        return mScaleType
    }

    override fun setFakeMatrix(matrix: Matrix?) {
        if (!matrixChanged(mMatrix, matrix)) return
        Log.d(TAG, "setFakeMatrix $matrix")
        mMatrix.set(matrix)
        configureBounds()
        invalidate()
    }

    override fun getFakeMatrix(): Matrix? {
        Log.d(TAG, "getFakeMatrix $mMatrix")
        return mMatrix
    }

    override fun setFakeScaleType(scaleType: ScaleType) {
        Log.d(TAG, "setFakeScaleType $scaleType")
        mScaleType = scaleType
        requestLayout()
        invalidate()
    }

    private fun updateDrawable() {
        configureBounds()
    }

    private fun configureBounds() {
        val dwidth = mDrawable!!.intrinsicWidth
        val dheight = mDrawable!!.intrinsicHeight
        mDrawable!!.setBounds(0, 0, dwidth, dheight)
    }

    override fun onDraw(canvas: Canvas?) {
        if (mDrawable == null) {
            return  // couldn't resolve the URI
        }

        val saveCount = canvas!!.saveCount
        canvas.concat(mMatrix)
        mDrawable!!.draw(canvas)
        canvas.restoreToCount(saveCount)
    }

}
