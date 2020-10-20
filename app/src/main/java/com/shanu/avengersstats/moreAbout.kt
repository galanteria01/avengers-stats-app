package com.shanu.avengersstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_more_about.*

class moreAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_about)

        val bun: Bundle? = intent.extras
        var name = bun?.getString("name")
        var subtitle = bun?.getString("subtitle")
        var image = bun!!.getInt("image")
        tvPicture.setImageResource(image)
        tvTitle.text = name
        tvSubtitle.text = subtitle



    }
}