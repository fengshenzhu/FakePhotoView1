package com.github.sethfeng.fakephotoview.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView.ScaleType
import com.example.atemktx.photoview.FakeDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

//        fakePhotoView.apply {
//            setFakeScaleType(ScaleType.CENTER)
//            setFakeDrawable(FakeDrawable())
//        }

        fakeTextureView.apply {
            setFakeScaleType(ScaleType.CENTER)
            setFakeDrawable(FakeDrawable())
        }

//            fakeView.apply {
//                setFakeScaleType(ScaleType.CENTER)
//                setFakeDrawable(FakeDrawable())
//            }

        }, 3_000)
    }
}
