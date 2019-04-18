package com.github.sethfeng.fakephotoview.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView.ScaleType
import com.example.atemktx.photoview.FakeDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fakePhotoView.apply {
            setScaleType(ScaleType.CENTER)
            setImageDrawable(FakeDrawable())
        }
    }
}
