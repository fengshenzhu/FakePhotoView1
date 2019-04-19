package com.github.sethfeng.fakephotoview.demo

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.SurfaceTexture
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.HandlerThread
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.TextureView
import android.view.ViewParent
import android.widget.ImageView
import com.github.sethfeng.fakephotoview.*
import com.github.sethfeng.fakephotoview.fake.Attachable
import com.github.sethfeng.fakephotoview.fake.FakeOnViewTapListener
import com.github.sethfeng.fakephotoview.fake.Fakeable

/**
 * Created by fengshzh on 04/18/2019.
 */
class FakeTextureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextureView(context, attrs, defStyleAttr), Fakeable,
    Attachable {

    companion object {
        private const val TAG = "FakeTextureView"
    }

    private var mDrawable: Drawable? = null
    private var mMatrix = Matrix()

    private var attacher: PhotoViewAttacher = PhotoViewAttacher(this)

    private var mDrawHandler: Handler

    private var mSurfaceDestroyed = true

    init {
        val drawThread = HandlerThread("FakeTextureView")
        drawThread.start()
        mDrawHandler = Handler(drawThread.looper)

        surfaceTextureListener = object : SurfaceTextureListener {
            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
                Log.d(TAG, "onSurfaceTextureSizeChanged: $width, $height")
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
                Log.d(TAG, "onSurfaceTextureUpdated")
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
                Log.d(TAG, "onSurfaceTextureDestroyed")
                mSurfaceDestroyed = true
                return true
            }

            override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
                Log.d(TAG, "onSurfaceTextureAvailable: $width, $height")
                mSurfaceDestroyed = false
                invalidateFake(mDrawable, mMatrix, mSurfaceDestroyed, mDrawHandler)
            }
        }
    }

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
        attacher.update()
    }

    override fun getFakeDrawable(): Drawable? {
        Log.d(TAG, "getFakeDrawable $mDrawable")
        return mDrawable
    }

    override fun getFakeScaleType(): ImageView.ScaleType {
//        Log.d(TAG, "getFakeScaleType $mScaleType")
        Log.d(TAG, "getFakeScaleType")
        return attacher.scaleType
    }

    override fun setFakeMatrix(matrix: Matrix?) {
        // TODO overscroll not refresh ui
//        if (!Util.matrixChanged(mMatrix, matrix)) return

        mMatrix.set(matrix)
        Log.d(TAG, "setFakeMatrix $mMatrix")
        configureBounds()
        invalidateFake(mDrawable, mMatrix, mSurfaceDestroyed, mDrawHandler)
    }

    override fun getFakeMatrix(): Matrix? {
//        Log.d(TAG, "getFakeMatrix $mMatrix")
        return attacher.imageMatrix
    }

    override fun setFakeScaleType(scaleType: ImageView.ScaleType) {
        Log.d(TAG, "setFakeScaleType $scaleType")
        attacher.scaleType = scaleType
    }

    private fun updateDrawable() {
        configureBounds()
    }

    private fun configureBounds() {
        val dwidth = mDrawable!!.intrinsicWidth
        val dheight = mDrawable!!.intrinsicHeight
        mDrawable!!.setBounds(0, 0, dwidth, dheight)
    }

    override fun setRotationTo(rotationDegree: Float) {
        attacher.setRotationTo(rotationDegree)
    }

    override fun setRotationBy(rotationDegree: Float) {
        attacher.setRotationBy(rotationDegree)
    }

    override fun isZoomable(): Boolean {
        return attacher.isZoomable()
    }

    override fun isXZoomable(): Boolean {
        return attacher.isXZoomable()
    }

    override fun isYZoomable(): Boolean {
        return attacher.isZoomable()
    }

    override fun setZoomable(zoomable: Boolean) {
        attacher.setZoomable(zoomable)
    }

    override fun setXZoomable(zoomable: Boolean) {
        attacher.setXZoomable(zoomable)
    }

    override fun setYZoomable(zoomable: Boolean) {
        attacher.setYZoomable(zoomable)
    }

    override fun getDisplayRect(): RectF {
        return attacher.getDisplayRect()
    }

    override fun getDisplayMatrix(matrix: Matrix) {
        attacher.getDisplayMatrix(matrix)
    }

    override fun setDisplayMatrix(finalRectangle: Matrix): Boolean {
        return attacher.setDisplayMatrix(finalRectangle)
    }

    override fun getSuppMatrix(matrix: Matrix) {
        attacher.getSuppMatrix(matrix)
    }

    override fun setSuppMatrix(matrix: Matrix): Boolean {
        return attacher.setDisplayMatrix(matrix)
    }

    override fun getMinimumScale(): Float {
        return attacher.getMinimumScale()
    }

    override fun getMediumScale(): Float {
        return attacher.getMediumScale()
    }

    override fun getMaximumScale(): Float {
        return attacher.getMaximumScale()
    }

    override fun getScale(): Float {
        return attacher.getScale()
    }

    override fun setAllowParentInterceptOnEdge(allow: Boolean) {
        attacher.setAllowParentInterceptOnEdge(allow)
    }

    override fun setMinimumScale(minimumScale: Float) {
        attacher.setMinimumScale(minimumScale)
    }

    override fun setMediumScale(mediumScale: Float) {
        attacher.setMediumScale(mediumScale)
    }

    override fun setMaximumScale(maximumScale: Float) {
        attacher.setMaximumScale(maximumScale)
    }

    override fun setScaleLevels(minimumScale: Float, mediumScale: Float, maximumScale: Float) {
        attacher.setScaleLevels(minimumScale, mediumScale, maximumScale)
    }

    override fun setOnMatrixChangeListener(listener: OnMatrixChangedListener) {
        attacher.setOnMatrixChangeListener(listener)
    }

    override fun setOnPhotoTapListener(listener: OnPhotoTapListener) {
        attacher.setOnPhotoTapListener(listener)
    }

    override fun setOnOutsidePhotoTapListener(listener: OnOutsidePhotoTapListener) {
        attacher.setOnOutsidePhotoTapListener(listener)
    }

    override fun setOnViewTapListener(listener: OnViewTapListener) {
        attacher.setOnViewTapListener(object : FakeOnViewTapListener {
            override fun onViewTap(fakeable: Fakeable, x: Float, y: Float) {
                listener.onViewTap(this@FakeTextureView, x, y)
            }
        })
    }

    override fun setOnViewDragListener(listener: OnViewDragListener) {
        attacher.setOnViewDragListener(listener)
    }

    override fun setScale(scale: Float) {
        attacher.setScale(scale)
    }

    override fun setScale(scale: Float, animate: Boolean) {
        attacher.setScale(scale, animate)
    }

    override fun setScale(scale: Float, focalX: Float, focalY: Float, animate: Boolean) {
        attacher.setScale(scale, focalX, focalY, animate)
    }

    override fun setZoomTransitionDuration(milliseconds: Int) {
        attacher.setZoomTransitionDuration(milliseconds)
    }

    override fun setOnDoubleTapListener(onDoubleTapListener: GestureDetector.OnDoubleTapListener) {
        attacher.setOnDoubleTapListener(onDoubleTapListener)
    }

    override fun setOnScaleChangeListener(onScaleChangedListener: OnScaleChangedListener) {
        attacher.setOnScaleChangeListener(onScaleChangedListener)
    }

    override fun setOnSingleFlingListener(onSingleFlingListener: OnSingleFlingListener) {
        attacher.setOnSingleFlingListener(onSingleFlingListener)
    }

    override fun invalidate() {
        super.invalidate()
        Log.d(TAG, "invalidate")
        // FIXME cannot draw here: 异步线程绘制时间太长(超过16ms)会一直收回调
//        invalidateFake(mDrawable, mMatrix, mSurfaceDestroyed, mDrawHandler)
    }

    private fun invalidateFake(drawable: Drawable?, matrix: Matrix, surfaceDestroyed: Boolean, handler: Handler) {
        if (drawable == null || surfaceDestroyed) {
            return
        }

        val clonedMatrix = Matrix(matrix)

        // post最新帧前丢掉队列里还未绘制的帧，否者绘制耗时导致累积误差，绘制越来越不跟手
        handler.removeCallbacksAndMessages(null)
        handler.post {
            if (drawable == null || surfaceDestroyed) return@post
            val canvas = lockCanvas() ?: return@post
            Log.d(TAG, "canvas: ${canvas.width}, ${canvas.height}")
            val saveCount = canvas.saveCount
            Log.d(TAG, "matrix: $clonedMatrix")
            canvas.concat(clonedMatrix)
            drawable!!.draw(canvas)
            canvas.restoreToCount(saveCount)
            unlockCanvasAndPost(canvas)
        }

//            if (mDrawable == null || mSurfaceDestroyed) return
//            val canvas = lockCanvas() ?: return
//            val saveCount = canvas.saveCount
//            canvas.concat(mMatrix)
//            mDrawable!!.draw(canvas)
//            canvas.restoreToCount(saveCount)
//            unlockCanvasAndPost(canvas)

    }
}