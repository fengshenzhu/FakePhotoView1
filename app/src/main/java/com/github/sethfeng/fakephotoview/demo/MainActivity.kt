package com.github.sethfeng.fakephotoview.demo

import android.graphics.Matrix
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView.ScaleType
import com.example.atemktx.photoview.FakeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Point().apply {
            windowManager.defaultDisplay.getSize(this)
            ScreenUtil.SCREEN_WIDTH = x
        }

        Handler().postDelayed({

//            val scaleType = ScaleType.MATRIX
//            val scaleType = ScaleType.FIT_XY
//            val scaleType = ScaleType.FIT_START
//            val scaleType = ScaleType.FIT_CENTER
//            val scaleType = ScaleType.FIT_END
            val scaleType = ScaleType.CENTER
//            val scaleType = ScaleType.CENTER_CROP
//            val scaleType = ScaleType.CENTER_INSIDE

            val num = 100.5f

            val endMatrix = Matrix()


            fakeView.apply {
                setFakeScaleType(scaleType)
                setFakeDrawable(FakeDrawable((ScreenUtil.SCREEN_WIDTH * num).toInt(), height))
                // init position: end
                getDisplayMatrix(endMatrix)
                endMatrix.setTranslate(
                    min(
                        -(getFakeDrawable()!!.intrinsicWidth - ScreenUtil.SCREEN_WIDTH) * 2,
                        0
                    ).toFloat(), 0f
                )
                setDisplayMatrix(endMatrix)
            }

            fakeImageView.apply {
                setFakeScaleType(scaleType)
                setFakeDrawable(resources.getDrawable(R.drawable.wallpaper))
            }

            fakeTextureView.apply {
                setFakeScaleType(scaleType)
                setFakeDrawable(FakeDrawable((ScreenUtil.SCREEN_WIDTH * num).toInt(), height))
            }

        }, 1_000)
    }
}
