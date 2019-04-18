package com.github.sethfeng.fakephotoview.fake

import android.content.Context
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewParent
import android.widget.ImageView.ScaleType

/**
 * Created by fengshzh on 04/18/2019.
 */
interface Fakeable {
    /** view start */
    fun getParentFake(): ViewParent

    fun getContextFake(): Context

    fun postFake(runnable: Runnable)

    fun setOnTouchListenerFake(listener: View.OnTouchListener)

    fun addOnLayoutChangeListenerFake(listener: View.OnLayoutChangeListener)

    fun isInEditModeFake(): Boolean

    fun getRightFake(): Int

    fun getBottomFake(): Int

    fun getWidthFake(): Int

    fun getPaddingLeftFake(): Int

    fun getPaddingRightFake(): Int

    fun getHeightFake(): Int

    fun getPaddingTopFake(): Int

    fun getPaddingBottomFake(): Int

    fun postOnAnimationFake(runnable: Runnable)
    /** view end */


    /** ImageView start */
    fun setFakeDrawable(drawable: Drawable?)

    fun getFakeDrawable(): Drawable?

    fun setFakeMatrix(matrix: Matrix?)

    fun getFakeMatrix(): Matrix?

    fun setFakeScaleType(scaleType: ScaleType)

    fun getFakeScaleType(): ScaleType
    /** ImageView end */
}