package com.github.sethfeng.fakephotoview.fake

import android.graphics.Matrix
import android.graphics.RectF
import android.view.GestureDetector
import com.github.sethfeng.fakephotoview.*

/**
 * Created by fengshzh on 04/18/2019.
 */
interface Attachable {

    fun setRotationTo(rotationDegree: Float)

    fun setRotationBy(rotationDegree: Float)

    fun isZoomable(): Boolean

    fun setZoomable(zoomable: Boolean)

    fun getDisplayRect(): RectF

    fun getDisplayMatrix(matrix: Matrix)

    fun setDisplayMatrix(finalRectangle: Matrix): Boolean

    fun getSuppMatrix(matrix: Matrix)

    fun setSuppMatrix(matrix: Matrix): Boolean

    fun getMinimumScale(): Float

    fun getMediumScale(): Float

    fun getMaximumScale(): Float

    fun getScale(): Float

    fun setAllowParentInterceptOnEdge(allow: Boolean)

    fun setMinimumScale(minimumScale: Float)

    fun setMediumScale(mediumScale: Float)

    fun setMaximumScale(maximumScale: Float)

    fun setScaleLevels(minimumScale: Float, mediumScale: Float, maximumScale: Float)

    fun setOnMatrixChangeListener(listener: OnMatrixChangedListener)

    fun setOnPhotoTapListener(listener: OnPhotoTapListener)

    fun setOnOutsidePhotoTapListener(listener: OnOutsidePhotoTapListener)

    fun setOnViewTapListener(listener: OnViewTapListener)

    fun setOnViewDragListener(listener: OnViewDragListener)

    fun setScale(scale: Float)

    fun setScale(scale: Float, animate: Boolean)

    fun setScale(scale: Float, focalX: Float, focalY: Float, animate: Boolean)

    fun setZoomTransitionDuration(milliseconds: Int)

    fun setOnDoubleTapListener(onDoubleTapListener: GestureDetector.OnDoubleTapListener)

    fun setOnScaleChangeListener(onScaleChangedListener: OnScaleChangedListener)

    fun setOnSingleFlingListener(onSingleFlingListener: OnSingleFlingListener)
}